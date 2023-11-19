package ru.vsu.cs.volchenko.linkmonitoringapp.fieldextractor

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import ru.vsu.cs.volchenko.linkmonitoringapp.fieldextractor.model.FieldExtractorRawExpression
import ru.vsu.cs.volchenko.linkmonitoringapp.fieldextractor.service.*
import ru.vsu.cs.volchenko.linkmonitoringapp.fieldextractor.util.ARRAY_MODIFIER_TOKEN

@Component
class FieldExtractor(
    val objectMapper: ObjectMapper
) {

    fun extract(raw: FieldExtractorRawExpression, json: String): String {

        val firstStep = FirstExpressionParserStep()
        var currentStep: AbstractExpressionParserStep = firstStep

        raw.expression.split(".").drop(1).forEach {
            val next = resolveStep(it)

            currentStep.next = next
            currentStep = next
        }

        return firstStep.tryExtract(objectMapper.readTree(json)).asText()
    }

    private fun resolveStep(step: String): AbstractExpressionParserStep {
        return when {
            step.startsWith(ARRAY_MODIFIER_TOKEN) -> ArrayExpressionParserStep(step)
            else -> GetFieldExpressionParserStep(step)
        }
    }
}