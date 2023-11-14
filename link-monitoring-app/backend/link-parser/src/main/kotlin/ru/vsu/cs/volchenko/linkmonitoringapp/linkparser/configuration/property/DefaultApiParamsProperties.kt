package ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.configuration.property

import jakarta.validation.constraints.Pattern
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.util.PATH_REGEX

data class DefaultApiParamsProperties(
    @field:Pattern(regexp = PATH_REGEX)
    var path: String = "",
    var headers: Map<String, String> = emptyMap(),
    var query: Map<String, String> = emptyMap()
)
