package ru.vsu.cs.volchenko.linkmonitoringapp.linkparser

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.configuration.LinkParserConfiguration
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.configuration.property.LinkSourceProperties
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.configuration.property.LinkTemplate
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.service.LinkTemplateParser
import java.net.URI

class LinkParserTest {

    val templateParser = LinkTemplateParser()
    val config : LinkParserConfiguration = mock()

    val linkParser = LinkParser(config, templateParser)

    @BeforeEach
    fun setup() {
        val firstSource : LinkSourceProperties = mock()
        whenever(firstSource.linkTemplate)
                .thenReturn(LinkTemplate("https://github.com/{owner}/{repository}"))

        val secondSource : LinkSourceProperties = mock()
        whenever(secondSource.linkTemplate)
                .thenReturn(LinkTemplate("https://stackoverflow.com/questions/{number}/{name}"))

        val thirdSource : LinkSourceProperties = mock()
        whenever(thirdSource.linkTemplate)
                .thenReturn(LinkTemplate("https://wildberries.ru/catalog/{number}/detail.aspx"))

        whenever(config.sources)
                .thenReturn(listOf(firstSource, secondSource, thirdSource))
    }

    @Test
    fun `test extractVariables happy path github`() {

        val res = linkParser.extractVariables(URI.create("https://github.com/owner/repository"))

        assertEquals(
                mapOf(
                        "owner" to "owner",
                        "repository" to "repository"
                ), res
        )
    }

    @Test
    fun `test extractVariables happy path stackoverflow`() {

        val res = linkParser.extractVariables(URI.create("https://stackoverflow.com/questions/44784328/how-to-obtain-all-subclasses-of-a-given-sealed-class"))

        assertEquals(
                mapOf(
                        "number" to "44784328",
                        "name" to "how-to-obtain-all-subclasses-of-a-given-sealed-class"
                ), res
        )
    }

    @Test
    fun `test extractVariables happy path wildberries`() {

        val res = linkParser.extractVariables(URI.create("https://wildberries.ru/catalog/155414203/detail.aspx"))

        assertEquals(
                mapOf(
                        "number" to "155414203"
                ), res
        )
    }
}