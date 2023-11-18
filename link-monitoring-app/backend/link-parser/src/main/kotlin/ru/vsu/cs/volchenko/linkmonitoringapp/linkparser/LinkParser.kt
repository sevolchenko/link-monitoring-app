package ru.vsu.cs.volchenko.linkmonitoringapp.linkparser

import org.springframework.stereotype.Component
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.configuration.RequiredLinkParserConfiguration
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.service.LinkTemplateParser
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.util.splitAsPath
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.util.withoutWww
import java.net.URI

@Component
class LinkParser(
        private val linkTemplateParser: LinkTemplateParser,
        private val config: RequiredLinkParserConfiguration
) {

    private val parsedLinkTemplates = config.linkTemplates()
            .map { linkTemplateParser.parse(it) }

    fun extractVariables(url: URI) : Map<String, String>{
        val parseResult = parsedLinkTemplates
                .firstOrNull { url.matches(it) } ?: error("Ссылка $url не совпадает ни с одним шаблоном")

        val urlParts = url.path.splitAsPath()

        return parseResult.path.variablesPlaces
                .mapValues { urlParts[it.value] }
    }

    private fun URI.matches(template: LinkTemplateParser.LinkTemplateParseResult) : Boolean {
        val parts = path.splitAsPath()

        return host.withoutWww() == template.host &&
                parts.size == template.path.variablesPlaces.size + template.path.pathPartsPlaces.size &&
                template.path.pathPartsPlaces
                        .all { parts[it.value] == it.key }

    }

}