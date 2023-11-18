package ru.vsu.cs.volchenko.linkmonitoringapp.fieldextractor.service

import ru.vsu.cs.volchenko.linkmonitoringapp.fieldextractor.util.ARRAY_NAME
import ru.vsu.cs.volchenko.linkmonitoringapp.fieldextractor.util.MODIFIER_TOKEN
import ru.vsu.cs.volchenko.linkmonitoringapp.fieldextractor.util.asJsonArray

class ArrayExpressionParserStep(
        private val operation: String
) : AbstractExpressionParserStep() {

    override fun extract(expr: String): String {
        // Убирает кавычки у обьектов внутри массива, хз как фиксить мб через JsonNode
        val array = objectMapper.readValue(expr.asJsonArray(), Map::class.java)[ARRAY_NAME] as List<*>

        return when {
            operation.startsWith(ITEM) -> {
                val index = operation.removePrefix(ITEM).toInt()

                array[index]
            }
            operation == LENGTH -> {
                array.size
            }
            else -> {
                error("Неподдерживаемая модификация массива, возможные значения: $ITEM, $LENGTH")
            }
        }.toString()
    }

    companion object {

        const val ITEM = "${MODIFIER_TOKEN}item"
        const val LENGTH = "${MODIFIER_TOKEN}lenght"

    }

}