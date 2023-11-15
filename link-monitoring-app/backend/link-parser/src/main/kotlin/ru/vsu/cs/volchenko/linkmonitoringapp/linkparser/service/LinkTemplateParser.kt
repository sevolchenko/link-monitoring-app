package ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.service

import org.springframework.stereotype.Component
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.extension.splitAsPath
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.extension.splitAsUrl
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.extension.withoutWww
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.model.LinkTemplate
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.model.PathTemplate
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.service.PathTemplateParser.PathTemplateParseResult
import java.net.URI

@Component
class LinkTemplateParser(
    val pathTemplateParser: PathTemplateParser
) {

    fun parse(template: LinkTemplate) : LinkTemplateParseResult {
        val (host, path) = template.url.splitAsUrl()

        val pathParseResult = pathTemplateParser.parse(PathTemplate(path))

        return LinkTemplateParseResult(
                host,
                pathParseResult
        )
    }


    data class LinkTemplateParseResult(
        val host: String,
        val path: PathTemplateParseResult
    )

}