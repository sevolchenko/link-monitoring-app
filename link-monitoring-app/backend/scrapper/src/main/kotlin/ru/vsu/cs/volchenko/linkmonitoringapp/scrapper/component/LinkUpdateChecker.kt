package ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.component

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import ru.vsu.cs.volchenko.linkmonitoringapp.fieldextractor.FieldExtractor
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.LinkParser
import ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.configuration.ScrapperConfiguration
import ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.dispatcher.KafkaEventsProducer
import ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.link.dao.LinkDao
import ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.link.dao.entity.Link
import java.time.OffsetDateTime.now

@Component
class LinkUpdateChecker(
        val dao: LinkDao,
        val linkParser: LinkParser,
        val fieldExtractor: FieldExtractor,
        val kafkaEventsProducer: KafkaEventsProducer,
        val configuration: ScrapperConfiguration
) {

    fun check() {

        val maxPossibleLastScannedAt = now().minus(configuration.linkCheckDelay)

        val links = dao.findAllByLastScannedAtBefore(maxPossibleLastScannedAt)
                .also { if (it.isEmpty()) log.info("Не найдено ссылок для проверки") }

        links.forEach { link ->

            link.lastScannedAt = now()

            check(link)?.also {

                log.info("По ссылке ${link.url} произошло событие $it")

                kafkaEventsProducer.sendEvent(it)

            } ?: log.info("Изменений по ссылке ${link.url} не найдено")

        }

    }

    fun check(link: Link) : String? {

        val url = link.url

        val paths = configuration.sources

        val pathParameters = linkParser.extractPathParameters(url)

        // TODO продумать алгоритм


    }

    companion object {
        private val log = LoggerFactory.getLogger(LinkUpdateChecker::class.java)
    }

}