package ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.util

const val URL_CHARS = "[-.a-zA-Z0-9:]+"

const val PROTOCOL_REGEX = "https?:\\/\\/"
const val PATH_PART_WITH_VARIABLE_REGEX = "\\/(($URL_CHARS)|(\\{$URL_CHARS\\}))"
const val PATH_PART_REGEX = "\\/$URL_CHARS"
const val HOST_REGEX = "($PROTOCOL_REGEX)?$URL_CHARS"
const val PATH_WITH_VARIABLES_REGEX = "($PATH_PART_WITH_VARIABLE_REGEX)*"
const val PATH_REGEX = "($PATH_PART_REGEX)*"

const val LINK_TEMPLATE_REGEX = "$HOST_REGEX$PATH_WITH_VARIABLES_REGEX"