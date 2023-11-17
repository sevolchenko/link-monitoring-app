package ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.configuration.property

data class DefaultApiParamsProperties(
    val headers: Map<String, String> = emptyMap(),
    val query: Map<String, String> = emptyMap()
)
