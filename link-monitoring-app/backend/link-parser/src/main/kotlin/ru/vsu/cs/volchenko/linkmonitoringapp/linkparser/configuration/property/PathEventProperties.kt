package ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.configuration.property

data class PathEventProperties(
    var name: String,
    var conditions: List<EventConditionProperties>
)
