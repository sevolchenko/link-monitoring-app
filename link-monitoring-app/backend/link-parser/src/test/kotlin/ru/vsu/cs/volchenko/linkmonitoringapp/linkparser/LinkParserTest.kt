package ru.vsu.cs.volchenko.linkmonitoringapp.linkparser

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.configuration.RequiredLinkParserConfiguration
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.service.LinkTemplateParser
import java.net.URI

class LinkParserTest {

    val linkTemplateParser: LinkTemplateParser = mock()
    val config: RequiredLinkParserConfiguration = mock()

    val linkParser = LinkParser(linkTemplateParser, config)

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