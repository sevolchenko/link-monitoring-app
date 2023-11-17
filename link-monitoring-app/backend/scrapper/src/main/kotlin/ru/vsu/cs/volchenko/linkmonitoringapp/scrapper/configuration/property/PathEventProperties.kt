package ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.configuration.property

import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
data class PathEventProperties(
    @field:NotBlank
    val name: String,
    @field:Valid
    val conditions: List<EventConditionProperties>
)
