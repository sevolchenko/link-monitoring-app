package ru.vsu.cs.volchenko.linkmonitoringapp.linkparser

import org.springframework.stereotype.Component
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.configuration.LinkParserConfiguration
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.extension.splitAsPath
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.service.LinkTemplateParser
import java.net.URI

@Component
class LinkParser(
        val config: LinkParserConfiguration,
        val parser: LinkTemplateParser
) {

    fun parse(url: URI) {

    }

    fun extractVariables(url: URI) : Map<String, String>{
        val parseResult = config.sources
                .map { parser.parse(it.linkTemplate) }
                .firstOrNull { parser.urlMatches(url, it) } ?: error("Ссылка $url не совпадает ни с одним шаблоном")

        val urlParts = url.path.splitAsPath()

        return parseResult.path.variablesPlaces
                .mapValues { urlParts[it.value] }
    }


}