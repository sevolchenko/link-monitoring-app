package ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.extension

fun String.splitAsUrl(
    skip: Int = 0,
    dropUntil: String = ""
) : List<String> = split("/")
    .drop(skip)
    .apply {
        if (dropUntil.isNotEmpty()) {
            dropWhile { it != dropUntil }
        }
    }