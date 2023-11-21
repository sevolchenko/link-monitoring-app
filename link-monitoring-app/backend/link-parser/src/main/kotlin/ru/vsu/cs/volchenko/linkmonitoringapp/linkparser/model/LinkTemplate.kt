package ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.model

import jakarta.validation.constraints.Pattern
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.util.LINK_TEMPLATE_REGEX

class LinkTemplate(
    @field:Pattern(regexp = LINK_TEMPLATE_REGEX)
    val url: String
)
