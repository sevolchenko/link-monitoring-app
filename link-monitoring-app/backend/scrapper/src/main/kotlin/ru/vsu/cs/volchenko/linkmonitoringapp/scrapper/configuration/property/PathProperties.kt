package ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.configuration.property

import jakarta.validation.Valid
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.model.BodyFieldExpression
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.model.PathTemplate

data class PathProperties(
        @field:Valid
        val path: PathTemplate,
        @field:Valid
        val bodyVariables: Map<String, BodyFieldExpression>,
        @field:Valid
        val events: List<PathEventProperties>
)
