package ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.extension

import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.util.PROTOCOL_REGEX
fun String.splitAsUrl(
): Pair<String, String> = replace(PROTOCOL_REGEX.toRegex(), "")
                .removePrefix("www.")
                .split("/", limit = 2)
                .let { it[0] to it[1] }
fun String.splitAsPath() : List<String> = split("/").dropWhile { it.isEmpty() }