package ru.vsu.cs.volchenko.linkmonitoringapp.fieldextractor.util

const val FIELD_CHARS = "[-_a-zA-Z]+"

const val ARRAY_MODIFIER_TOKEN = "$"

const val ARRAY_MODIFIER_REGEX = "\\$ARRAY_MODIFIER_TOKEN$FIELD_CHARS[0-9]*"

const val EXPRESSION_REGEX = "\\$ARRAY_MODIFIER_TOKEN(\\.(($FIELD_CHARS)|($ARRAY_MODIFIER_REGEX)))+"
