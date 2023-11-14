package ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.service

import org.springframework.stereotype.Component
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.configuration.property.LinkTemplate
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.extension.splitAsUrl
import java.net.URI

@Component
class LinkTemplateParser {

    fun parse(template: LinkTemplate) : LinkTemplateParseResult {
        return with(template.source) {
            if (!matches(Regex(LINK_TEMPLATE_REGEXP)))
                error("Шаблон из конфигурации $this не совпадает с LINK_TEMPLATE_REGEXP")

            // Отсекаем протокол и пустоту между //
            val parts = splitAsUrl(skip = 2) // TODO case without protocol

            val host = parts[0]

            // Отсекли хост
            val variables = parts // TODO case without www.
                    .drop(1)
                    .mapIndexed { index, part -> part to index }
                    .filter { isVariable(it.first) }
                    .associate { it.first.removeSurrounding(prefix = "{", suffix = "}") to it.second }

            val pathParts = parts
                    .drop(1)
                    .mapIndexed { index, part -> part to index }
                    .filter { !isVariable(it.first) }
                    .associate { it.first to it.second }


            LinkTemplateParseResult(
                host,
                pathParts,
                variables
            )
        }
    }

    fun checkUrl(parsedTemplate: LinkTemplateParseResult, url: URI) : Boolean {
        val parts = url.path.splitAsUrl(skip = 1)

        return url.host == parsedTemplate.host &&
                parsedTemplate.variablesPlaces.size + parsedTemplate.pathPartsPlaces.size == parts.size &&
                parsedTemplate.pathPartsPlaces
                        .all { it.key == parts[it.value] }
    }

    private fun isVariable(part: String) = part.startsWith("{") && part.endsWith("}")


    data class LinkTemplateParseResult(
        val host: String,
        val pathPartsPlaces: Map<String, Int>,
        val variablesPlaces: Map<String, Int>
    )

    companion object {
        private const val LINK_TEMPLATE_REGEXP = "(https?:\\/\\/)?([-a-zA-Z0-9:]\\.?)+(\\/(([-.a-zA-Z0-9]+)|(\\{[-a-zA-Z0-9]+\\})))*"
    }

}