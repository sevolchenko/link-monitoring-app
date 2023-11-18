package ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.extension

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.util.splitAsUrl

class LinkExtensionTest {

    @ParameterizedTest
    @CsvSource(
        "https://, www.",
        "http://, www.",
        "'', www.",
        "'', www.",
        "https://, ''",
        "http://, ''",
        "'', ''",
    )
    fun `split with https and www`(
        protocol: String,
        www: String
    ) {
        val url = "$protocol${www}stackoverflow.com/questions/{number}/{name}"

        val (host, path) = url.splitAsUrl()

        assertEquals("stackoverflow.com", host)
        assertEquals("questions/{number}/{name}", path)
    }

}