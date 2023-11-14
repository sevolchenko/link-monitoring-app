package ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.configuration.property

import jakarta.validation.Valid
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.model.BodyFieldExpression
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.model.PathTemplate

data class PathProperties(
        @field:Valid
        var path: PathTemplate,
        @field:Valid
        var bodyVariables: Map<String, BodyFieldExpression>,
        @field:Valid
        var events: List<PathEventProperties>
)
