package ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.configuration.property

data class ApiProperties(
    var host: String,
    var headers: Map<String, String> = mapOf(),
    var queryParams: Map<String, String> = mapOf(),
    var paths: List<PathProperties>
)
