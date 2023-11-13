package ru.vsu.cs.volchenko.linkmonitoringapp.linkparser

import org.springframework.stereotype.Component
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.configuration.LinkParserConfiguration
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.extension.splitAsUrl
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
                .firstOrNull { parser.checkUrl(it, url) } ?: error("Ссылка $url не совпадает ни с одним шаблоном")

        // Нулевой элемент - пустой, поэтому убираем его
        val urlParts = url.path.splitAsUrl(skip = 1)

        return parseResult.variablesPlaces
                .mapValues { urlParts[it.value] }
    }


}