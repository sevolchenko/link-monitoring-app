package ru.vsu.cs.volchenko.linkmonitoringapp.linkparser

import org.springframework.stereotype.Component
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.configuration.LinkParserConfiguration
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.extension.splitAsPath
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.service.LinkTemplateParser
import java.net.URI

@Component
class LinkParser(
        config: LinkParserConfiguration,
        private val parser: LinkTemplateParser
) {

    private val parsedLinkTemplates = config.sources
            .map { parser.parse(it.linkTemplate) }

    fun extractVariables(url: URI) : Map<String, String>{
        val parseResult = parsedLinkTemplates
                .firstOrNull { parser.urlMatches(url, it) } ?: error("Ссылка $url не совпадает ни с одним шаблоном")

        val urlParts = url.path.splitAsPath()

        return parseResult.path.variablesPlaces
                .mapValues { urlParts[it.value] }
    }


}