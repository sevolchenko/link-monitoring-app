package ru.vsu.cs.volchenko.linkmonitoringapp.fieldextractor.service

import com.fasterxml.jackson.databind.JsonNode

class FirstExpressionParserStep : AbstractExpressionParserStep() {

    override fun extract(tree: JsonNode): JsonNode {
        return tree
    }
}
