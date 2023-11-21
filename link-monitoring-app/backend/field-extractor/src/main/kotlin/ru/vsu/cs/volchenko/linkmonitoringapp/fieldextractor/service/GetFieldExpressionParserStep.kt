package ru.vsu.cs.volchenko.linkmonitoringapp.fieldextractor.service

import com.fasterxml.jackson.databind.JsonNode

class GetFieldExpressionParserStep(
        private val field: String
) : AbstractExpressionParserStep() {

    override fun extract(tree: JsonNode): JsonNode {
        return tree[field] ?: error("Пришел null либо такого поля не существует")
    }

}