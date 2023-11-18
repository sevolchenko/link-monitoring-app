package ru.vsu.cs.volchenko.linkmonitoringapp.fieldextractor.service

class GetFieldExpressionParserStep(
        private val field: String
) : AbstractExpressionParserStep() {

    override fun extract(expr: String): String {
        val map = objectMapper.readValue(expr, Map::class.java)

        return map[field].toString()
    }

}