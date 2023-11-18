package ru.vsu.cs.volchenko.linkmonitoringapp.fieldextractor

import org.springframework.stereotype.Component
import ru.vsu.cs.volchenko.linkmonitoringapp.fieldextractor.model.FieldExtractorRawExpression
import ru.vsu.cs.volchenko.linkmonitoringapp.fieldextractor.service.AbstractExpressionParserStep
import ru.vsu.cs.volchenko.linkmonitoringapp.fieldextractor.service.ArrayExpressionParserStep
import ru.vsu.cs.volchenko.linkmonitoringapp.fieldextractor.service.FirstExpressionParserStep
import ru.vsu.cs.volchenko.linkmonitoringapp.fieldextractor.service.GetFieldExpressionParserStep
import ru.vsu.cs.volchenko.linkmonitoringapp.fieldextractor.util.MODIFIER_TOKEN

@Component
class FieldExtractor {

    fun extract(raw: FieldExtractorRawExpression, json: String): String {

        val firstStep = FirstExpressionParserStep()
        var currentStep : AbstractExpressionParserStep = firstStep

        raw.expression.split(".").drop(1).forEach {

            currentStep.next = if (it.startsWith(MODIFIER_TOKEN)) {
                ArrayExpressionParserStep(it)
            } else {
                GetFieldExpressionParserStep(it)
            }

            currentStep = currentStep.next ?: error("Ошибка парсинга")

        }

        return firstStep.tryExtract(json)
    }
}