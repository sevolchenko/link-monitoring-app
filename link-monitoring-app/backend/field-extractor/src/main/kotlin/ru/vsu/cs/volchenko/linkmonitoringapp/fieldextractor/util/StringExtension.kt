package ru.vsu.cs.volchenko.linkmonitoringapp.fieldextractor.util

fun String.toJsonObject() = if (!(startsWith("{") && endsWith("}"))) {
    "{$this}"
} else {
    this
}

fun String.asJsonArray() = insertString("\"${ARRAY_NAME}\":", 1)

fun String.insertString(string: String, index: Int) =
    StringBuilder(this).apply { insert(index, string) }.toString()

const val ARRAY_NAME = "@ARRAY"
