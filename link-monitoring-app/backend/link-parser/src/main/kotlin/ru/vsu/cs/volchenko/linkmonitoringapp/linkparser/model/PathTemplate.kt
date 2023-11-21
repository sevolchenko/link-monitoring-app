package ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.model

import jakarta.validation.constraints.Pattern
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.util.PATH_WITH_VARIABLES_REGEX

data class PathTemplate(
    @field:Pattern(regexp = PATH_WITH_VARIABLES_REGEX)
    val path: String
)
