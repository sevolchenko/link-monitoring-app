package ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.extension

fun String.splitAsUrl(skip: Int = 0) : List<String> = split("/").drop(skip)