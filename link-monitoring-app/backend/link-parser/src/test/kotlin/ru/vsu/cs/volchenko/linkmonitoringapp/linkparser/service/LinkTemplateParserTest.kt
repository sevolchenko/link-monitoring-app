package ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.configuration.property.LinkTemplate

class LinkTemplateParserTest {

    val parser = LinkTemplateParser()

    @Test
    fun `happy path`() {
        val template = LinkTemplate("https://github.com/{owner}/{repository}")

        val res = parser.parse(template)

        assertEquals("github.com", res.host)
        assertEquals(
                mapOf(
                        "owner" to 0,
                        "repository" to 1
                ),
                res.variablesPlaces
        )
    }

}