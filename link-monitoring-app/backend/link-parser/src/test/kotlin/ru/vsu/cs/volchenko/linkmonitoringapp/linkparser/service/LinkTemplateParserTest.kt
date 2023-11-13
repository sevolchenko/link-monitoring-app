package ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.configuration.property.LinkTemplate

class LinkTemplateParserTest {

    val parser = LinkTemplateParser()

    @Test
    fun `happy path github`() {
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
        assertEquals(
                mapOf<String, Int>(),
                res.pathPartsPlaces
        )
    }

    @Test
    fun `happy path stackoverflow`() {
        val template = LinkTemplate("https://stackoverflow.com/questions/{number}/{name}")

        val res = parser.parse(template)

        assertEquals("stackoverflow.com", res.host)
        assertEquals(
                mapOf(
                    "number" to 1,
                    "name" to 2
                ),
                res.variablesPlaces
        )
        assertEquals(
                mapOf(
                    "questions" to 0
                ),
                res.pathPartsPlaces
        )
    }

}