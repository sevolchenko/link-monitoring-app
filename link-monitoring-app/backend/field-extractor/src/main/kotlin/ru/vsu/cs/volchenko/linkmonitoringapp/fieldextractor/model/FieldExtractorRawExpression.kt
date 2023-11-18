package ru.vsu.cs.volchenko.linkmonitoringapp.fieldextractor.model

import jakarta.validation.constraints.Pattern
import ru.vsu.cs.volchenko.linkmonitoringapp.fieldextractor.util.EXPRESSION_REGEX

class FieldExtractorRawExpression(
    @field:Pattern(regexp = EXPRESSION_REGEX)
    val expression: String
) {
}