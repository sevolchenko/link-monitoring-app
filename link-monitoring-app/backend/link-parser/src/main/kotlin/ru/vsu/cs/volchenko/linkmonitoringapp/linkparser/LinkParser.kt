package ru.vsu.cs.volchenko.linkmonitoringapp.linkparser

import org.springframework.stereotype.Component
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.configuration.LinkParserConfiguration
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.extension.splitAsPath
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.extension.withoutWww
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.service.LinkTemplateParser
import java.net.URI

@Component
class LinkParser(
        private val config: LinkParserConfiguration,
        private val parser: LinkTemplateParser
) {

    private val parsedLinkTemplates = config.sources
            .map { parser.parse(it.linkTemplate) }

    fun extractVariables(url: URI) : Map<String, String>{
        val parseResult = parsedLinkTemplates
                .firstOrNull { url.matches(it) } ?: error("Ссылка $url не совпадает ни с одним шаблоном")

        val urlParts = url.path.splitAsPath()

        return parseResult.path.variablesPlaces
                .mapValues { urlParts[it.value] }
    }

    fun URI.matches(template: LinkTemplateParser.LinkTemplateParseResult) : Boolean {
        val parts = path.splitAsPath()

        return host.withoutWww() == template.host &&
                parts.size == template.path.variablesPlaces.size + template.path.pathPartsPlaces.size &&
                template.path.pathPartsPlaces
                        .all { parts[it.value] == it.key }

    }

}