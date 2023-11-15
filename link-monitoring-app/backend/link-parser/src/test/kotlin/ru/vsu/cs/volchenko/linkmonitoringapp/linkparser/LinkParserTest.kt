package ru.vsu.cs.volchenko.linkmonitoringapp.linkparser

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.junit4.SpringRunner
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.configuration.LinkParserConfiguration
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.configuration.property.LinkSourceProperties
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.model.LinkTemplate
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.service.LinkTemplateParser
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.service.PathTemplateParser
import java.net.URI

@SpringBootTest(classes = [LinkParser::class, LinkTemplateParser::class, PathTemplateParser::class, LinkParserConfiguration::class ])
@ExtendWith(SpringExtension::class)
class LinkParserTest {

    @Autowired
    lateinit var linkParser: LinkParser

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