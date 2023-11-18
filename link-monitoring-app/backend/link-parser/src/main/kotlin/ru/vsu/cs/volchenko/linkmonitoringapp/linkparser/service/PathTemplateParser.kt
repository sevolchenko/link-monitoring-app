package ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.service

import jakarta.validation.Valid
import org.springframework.stereotype.Component
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.model.PathTemplate
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.util.splitAsPath

@Component
class PathTemplateParser {

    fun parse(@Valid template: PathTemplate): PathTemplateParseResult {
        val parts = template.path.splitAsPath()

        val variables = parts
            .mapIndexed { index, part -> part to index }
            .filter { isVariable(it.first) }
            .associate { it.first.removeSurrounding(prefix = "{", suffix = "}") to it.second }

        val pathParts = parts
            .mapIndexed { index, part -> part to index }
            .filter { !isVariable(it.first) }
            .associate { it.first to it.second }

        return PathTemplateParseResult(pathParts, variables)
    }

    private fun isVariable(part: String) = part.startsWith("{") && part.endsWith("}")

    data class PathTemplateParseResult (
        val pathPartsPlaces: Map<String, Int>,
        val variablesPlaces: Map<String, Int>
    )

}