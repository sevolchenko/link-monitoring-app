package ru.vsu.cs.volchenko.linkmonitoringapp.fieldextractor.util

const val FIELD_CHARS = "[-_a-zA-Z]+"

const val MODIFIER_TOKEN = "$"

const val ARRAY_MODIFIER_REGEX = "$MODIFIER_TOKEN$FIELD_CHARS[0-9]+?"

const val EXPRESSION_REGEX = "$MODIFIER_TOKEN(\\.($FIELD_CHARS)|$($ARRAY_MODIFIER_REGEX))+"
