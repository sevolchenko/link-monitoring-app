package ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.configuration.property

import jakarta.validation.Valid
import jakarta.validation.constraints.Pattern
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.util.HOST_REGEX
import java.net.URI

data class ApiProperties(
    val host: URI,
    @field:Valid
    val defaultParams: DefaultApiParamsProperties = DefaultApiParamsProperties(),
)
