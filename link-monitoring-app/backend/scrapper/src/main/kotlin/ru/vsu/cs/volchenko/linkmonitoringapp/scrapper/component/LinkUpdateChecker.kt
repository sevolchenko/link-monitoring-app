package ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.component

import com.fasterxml.jackson.databind.JsonNode
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import ru.vsu.cs.volchenko.linkmonitoringapp.fieldextractor.FieldExtractor
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.LinkParser
import ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.configuration.ScrapperConfiguration
import ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.configuration.property.EventConditionProperties
import ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.configuration.property.EventConditionProperties.EventChangeType.DEC
import ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.configuration.property.EventConditionProperties.EventChangeType.INC
import ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.configuration.property.PathEventProperties
import ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.dispatcher.KafkaEventsProducer
import ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.link.dao.LinkDao
import ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.link.dao.entity.Link
import java.net.URI
import java.time.OffsetDateTime.now

@Component
class LinkUpdateChecker(
        val dao: LinkDao,
        val linkParser: LinkParser,
        val fieldExtractor: FieldExtractor,
        val kafkaEventsProducer: KafkaEventsProducer,
        val apiWebClientMaker: ApiWebClientMaker,
        val configuration: ScrapperConfiguration
) {

    fun check() {

        val maxPossibleLastScannedAt = now().minus(configuration.linkCheckDelay)

        val links = dao.findAllByLastScannedAtBefore(maxPossibleLastScannedAt)
                .also { if (it.isEmpty()) log.info("Не найдено ссылок для проверки") }

        links.forEach { link ->

            val linkSource = configuration.sources.first { it.linkTemplate == linkParser.findTemplate(link.url) }

            val webClient = apiWebClientMaker.make(linkSource.api)

            val pathParameters = linkParser.extractPathParameters(link.url)

            linkSource.paths.forEach { pathTemplate ->

                val apiState = webClient
                    .get()
                    .uri {
                        // TODO: build correct webclient
                        pathParameters
                        URI.create("")
                    }
                    .retrieve()
                    .bodyToMono(JsonNode::class.java)
                    .block() // TODO block or subscribe ASYNC maybe
                    ?: error("С вызова ${linkSource.api.host}${pathTemplate.path} получен null")

                // TODO unpack vars via fieldExtractor
                val apiVariables = emptyMap<String, String>()

                val currentState = link.state.fields().asSequence().associate { it.key to it.value.asText() }

                pathTemplate.events.forEach { event ->

                    if (event.happened(currentState, apiVariables)) {

                        log.info("По ссылке ${link.url} произошло событие ${event.name}")

                        kafkaEventsProducer.sendEvent(event.name)

                    }

                }
            }

            link.lastScannedAt = now()

        }

    }

    private fun PathEventProperties.happened(
        currVariables: Map<String, String>, apiVariables: Map<String, String>
    ): Boolean {
        return conditions.all {
            val currValue = currVariables[it.variable]
                ?: error("Невозможная ситуация, в базе отсутствует значение ${it.variable}")
            val apiValue = apiVariables[it.variable]
                ?: error("Невозможная ситуация, в api отсутствует значение ${it.variable}")

            return it.changeType.compare(currValue, apiValue)
        }
    }

    private fun EventConditionProperties.EventChangeType.compare(
        currValue: String, apiValue: String
    ): Boolean {
        return when (this) {
            INC -> apiValue > currValue
            DEC -> apiValue < currValue
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(LinkUpdateChecker::class.java)
    }

}