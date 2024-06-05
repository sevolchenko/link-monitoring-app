package ru.vsu.cs.volchenko.linkmonitoringapp.fieldextractor

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import ru.vsu.cs.volchenko.linkmonitoringapp.fieldextractor.model.FieldExtractorRawExpression
import ru.vsu.cs.volchenko.linkmonitoringapp.fieldextractor.service.AbstractExpressionParserStep
import ru.vsu.cs.volchenko.linkmonitoringapp.fieldextractor.service.ArrayExpressionParserStep
import ru.vsu.cs.volchenko.linkmonitoringapp.fieldextractor.service.FirstExpressionParserStep
import ru.vsu.cs.volchenko.linkmonitoringapp.fieldextractor.service.GetFieldExpressionParserStep
import ru.vsu.cs.volchenko.linkmonitoringapp.fieldextractor.util.ARRAY_MODIFIER_TOKEN

@Component
class FieldExtractor {

    fun extract(variables: Map<String, FieldExtractorRawExpression>, tree: JsonNode): Map<String, String> {

        val firstStep = FirstExpressionParserStep()
        var currentStep: AbstractExpressionParserStep = firstStep

        return variables.mapValues { variable ->

            variable.value.expression.split(".").drop(1).forEach {
                currentStep = resolveStep(it).also {
                    nextStep -> currentStep.next = nextStep
                }
            }

            firstStep.tryExtract(tree).asText()
        }
    }

    private fun resolveStep(step: String): AbstractExpressionParserStep {
        return when {
            step.startsWith(ARRAY_MODIFIER_TOKEN) -> ArrayExpressionParserStep(step)
            else -> GetFieldExpressionParserStep(step)
        }
    }
}
