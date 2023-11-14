package ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.configuration.property

import jakarta.validation.Valid
import jakarta.validation.constraints.Pattern
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.util.HOST_REGEX

data class ApiProperties(
    @field:Pattern(regexp = HOST_REGEX)
    var host: String,
    @field:Valid
    var defaultParams: DefaultApiParamsProperties = DefaultApiParamsProperties(),
    @field:Valid
    var paths: List<PathProperties>
)
