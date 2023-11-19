package ru.vsu.cs.volchenko.linkmonitoringapp.fieldextractor.service

import com.fasterxml.jackson.databind.DatabindException
import com.fasterxml.jackson.databind.JsonNode

abstract class AbstractExpressionParserStep {

    var next: AbstractExpressionParserStep? = null

    fun tryExtract(tree: JsonNode) : JsonNode {
        return try {
            val extracted = extract(tree)
            next?.tryExtract(extracted) ?: extracted
        } catch (ex: DatabindException) {
            error("Ошибка парсинга: ${ex.message}")
        }
    }

    protected abstract fun extract(tree: JsonNode) : JsonNode

}