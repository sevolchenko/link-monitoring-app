package ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.configuration.property

import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
data class PathEventProperties(
    @field:NotBlank
    var name: String,
    @field:Valid
    var conditions: List<EventConditionProperties>
)
