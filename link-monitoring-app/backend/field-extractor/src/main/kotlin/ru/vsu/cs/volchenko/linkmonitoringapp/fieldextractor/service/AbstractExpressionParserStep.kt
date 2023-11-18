package ru.vsu.cs.volchenko.linkmonitoringapp.fieldextractor.service

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DatabindException
import com.fasterxml.jackson.databind.ObjectMapper
import ru.vsu.cs.volchenko.linkmonitoringapp.fieldextractor.util.toJsonObject

abstract class AbstractExpressionParserStep {

    val objectMapper = ObjectMapper()
            .disable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)

    var next: AbstractExpressionParserStep? = null

    fun tryExtract(expr: String) : String {
        return try {
            val extracted = extract(expr.toJsonObject())
            next?.tryExtract(extracted) ?: extracted
        } catch (ex: DatabindException) {
            error("Неверный парсинг")
        }
    }

    protected abstract fun extract(expr: String) : String

}