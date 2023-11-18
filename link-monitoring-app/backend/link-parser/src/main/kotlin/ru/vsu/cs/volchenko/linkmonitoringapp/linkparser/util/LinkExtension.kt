package ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.util

fun String.splitAsUrl(
): Pair<String, String> = withoutProtocol()
                .withoutWww()
                .split("/", limit = 2)
                .let { it[0] to it[1] }

fun String.withoutProtocol() = replaceFirst(PROTOCOL_REGEX.toRegex(), "")

fun String.withoutWww() = removePrefix("www.")

fun String.splitAsPath() : List<String> = split("/").dropWhile { it.isEmpty() }