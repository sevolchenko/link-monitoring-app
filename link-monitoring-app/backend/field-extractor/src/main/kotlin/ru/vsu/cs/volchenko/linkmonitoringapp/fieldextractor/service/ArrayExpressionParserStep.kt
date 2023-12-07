package ru.vsu.cs.volchenko.linkmonitoringapp.fieldextractor.service

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.IntNode
import ru.vsu.cs.volchenko.linkmonitoringapp.fieldextractor.util.ARRAY_MODIFIER_TOKEN

class ArrayExpressionParserStep(
    private val operation: String
) : AbstractExpressionParserStep() {

    override fun extract(tree: JsonNode): JsonNode {
        if (tree !is ArrayNode) error("Ожидается массив")

        return PermittedArrayOperation.values()
            .firstOrNull { it.condition(operation) }
            ?.action?.invoke(operation, tree)
            ?: error(
                "Неподдерживаемая модификация массива, возможные значения: ${
                    PermittedArrayOperation.values().joinToString { it.example }
                }"
            )
    }

    private enum class PermittedArrayOperation(
        val example: String,
        val condition: (operation: String) -> Boolean,
        val action: (operation: String, tree: ArrayNode) -> JsonNode
    ) {

        GET_ITEM_OPERATION(
            "${ARRAY_MODIFIER_TOKEN}item",
            { operation: String -> operation.startsWith(GET_ITEM_OPERATION.example) },
            { operation: String, tree: JsonNode ->
                val index = operation.removePrefix(GET_ITEM_OPERATION.example).toInt()
                tree[index]
            }
        ),

        LENGTH_OPERATION(
            "${ARRAY_MODIFIER_TOKEN}lenght",
            { operation: String -> operation == LENGTH_OPERATION.example },
            { _: String, tree: JsonNode ->
                // TODO мб дать имя?
                IntNode(tree.size())
            }
        )
    }
}
