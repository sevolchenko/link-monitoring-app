package ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.configuration.property

import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.model.LinkTemplate

data class LinkSourceProperties(
        @field:NotBlank
    val name: String,
        @field:Valid
    val linkTemplate: LinkTemplate,
        @field:Valid
    val api: ApiProperties,
)
