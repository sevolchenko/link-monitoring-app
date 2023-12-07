package ru.vsu.cs.volchenko.linkmonitoringapp.linkparser

import org.springframework.stereotype.Component
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.configuration.RequiredLinkParserConfiguration
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.model.LinkTemplate
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.service.LinkTemplateParser
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.util.splitAsPath
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.util.withoutWww
import java.net.URI

@Component
class LinkParser(
        private val linkTemplateParser: LinkTemplateParser,
        private val config: RequiredLinkParserConfiguration
) {

    fun extractPathParameters(url: URI) : Map<String, String>{

        val template = findTemplate(url) ?: error("Ссылка $url не совпадает ни с одним шаблоном")

        val parseResult = linkTemplateParser.parse(template)

        val urlParts = url.path.splitAsPath()

        return parseResult.path.variablesPlaces
                .mapValues { urlParts[it.value] }
    }

    fun findTemplate(url: URI) : LinkTemplate? = config.linkTemplates().firstOrNull { url.matches(it) }

    private fun URI.matches(template: LinkTemplate) : Boolean {
        val parseResult = linkTemplateParser.parse(template)

        val parts = path.splitAsPath()

        return host.withoutWww() == parseResult.host &&
                parts.size == parseResult.path.variablesPlaces.size + parseResult.path.pathPartsPlaces.size &&
                parseResult.path.pathPartsPlaces
                        .all { parts[it.value] == it.key }
    }

}
