package ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.model.PathTemplate

class PathTemplateParserTest {

    val parser = PathTemplateParser()

    @Test
    fun `happy path without pathParts`() {
        val url = "/{owner}/{repository}"

        val res = parser.parse(PathTemplate(url))

        assertEquals(
            mapOf(
                "owner" to 0,
                "repository" to 1
                ),
            res.variablesPlaces
        )

        assertEquals(
            emptyMap<String, Int>(),
            res.pathPartsPlaces
        )
    }

    @Test
    fun `happy path with pathParts`() {
        val url = "/{owner}/part/{repository}"

        val res = parser.parse(PathTemplate(url))

        assertEquals(
            mapOf(
                "owner" to 0,
                "repository" to 2
            ),
            res.variablesPlaces
        )

        assertEquals(
            mapOf(
                "part" to 1
            ),
            res.pathPartsPlaces
        )
    }

}