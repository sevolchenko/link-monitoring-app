package ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.configuration.property

import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.model.LinkTemplate

data class LinkSourceProperties(
    @field:NotBlank
    var name: String,
    @field:Valid
    var linkTemplate: LinkTemplate,
    @field:Valid
    var api: ApiProperties,
)
