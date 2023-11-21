package ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.model.LinkTemplate
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.service.PathTemplateParser.PathTemplateParseResult

class LinkTemplateParserTest {

    val pathParser: PathTemplateParser = mock()
    val parser = LinkTemplateParser(pathParser)

    @Test
    fun `happy path github`() {
        val pathTemplateResult = PathTemplateParseResult(
            mapOf(
                "owner" to 0,
                "repository" to 1
            ),
            emptyMap()
        )

        whenever(pathParser.parse(any()))
            .thenReturn(pathTemplateResult)

        val template = LinkTemplate("https://github.com/{owner}/{repository}")

        val res = parser.parse(template)

        assertEquals("github.com", res.host)
        assertEquals(
            pathTemplateResult,
            res.path
        )
    }

    @Test
    fun `happy path stackoverflow`() {
        val pathTemplateResult = PathTemplateParseResult(
            mapOf(
                "number" to 1,
                "name" to 2
            ),
            mapOf(
                "questions" to 0
            )
        )

        whenever(pathParser.parse(any()))
            .thenReturn(pathTemplateResult)

        val template = LinkTemplate("https://stackoverflow.com/questions/{number}/{name}")

        val res = parser.parse(template)

        assertEquals("stackoverflow.com", res.host)
        assertEquals(
            pathTemplateResult,
            res.path
        )
    }

}
