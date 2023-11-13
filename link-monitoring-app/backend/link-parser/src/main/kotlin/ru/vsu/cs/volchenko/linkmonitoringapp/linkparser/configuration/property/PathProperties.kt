package ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.configuration.property

data class PathProperties(
        var path: PathTemplate,
        var variables: Map<String, BodyFieldExpression>,
        var events: List<PathEventProperties>
)
