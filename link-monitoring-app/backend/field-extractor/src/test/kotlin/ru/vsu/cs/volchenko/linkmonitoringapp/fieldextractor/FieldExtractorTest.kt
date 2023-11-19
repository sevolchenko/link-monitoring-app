package ru.vsu.cs.volchenko.linkmonitoringapp.fieldextractor

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.vsu.cs.volchenko.linkmonitoringapp.fieldextractor.model.FieldExtractorRawExpression

class FieldExtractorTest {

    val objectMapper = ObjectMapper()
            .registerModule(KotlinModule.Builder()
                    .withReflectionCacheSize(512)
                    .configure(KotlinFeature.NullToEmptyCollection, false)
                    .configure(KotlinFeature.NullToEmptyMap, false)
                    .configure(KotlinFeature.NullIsSameAsDefault, false)
                    .configure(KotlinFeature.SingletonSupport, false)
                    .configure(KotlinFeature.StrictNullChecks, false)
                    .build())

    val fieldExtractor = FieldExtractor(objectMapper)

    @Test
    fun `happy path getfield`() {

        val expr = "$.digit"
        val json = objectMapper.writeValueAsString(
                object {
                    val digit = 5
                    val string = "test"
                }
        )

        val res = fieldExtractor.extract(FieldExtractorRawExpression(expr), json)

        assertEquals("5", res)

    }

    @Test
    fun `happy path array item`() {

        val expr = "$.items.\$item1"
        val json = objectMapper.writeValueAsString(
                object {
                    val items = listOf("first", "second", "third")
                }
        )

        val res = fieldExtractor.extract(FieldExtractorRawExpression(expr), json)

        assertEquals("second", res)

    }
    @Test
    fun `happy path array lenght`() {

        val expr = "$.items.\$lenght"
        val json = objectMapper.writeValueAsString(
                object {
                    val items = listOf("first", "second", "third")
                }
        )

        val res = fieldExtractor.extract(FieldExtractorRawExpression(expr), json)

        assertEquals("3", res)

    }

    @Test
    fun `happy path classes array`() {

        class Example (val value : Int, val prefix: String)

        val expr = "$.items.\$item1.prefix"
        val json = "{\"items\":[{\"value\":0,\"prefix\":\"pre0\"},{\"value\":1,\"prefix\":\"pre1\"},{\"value\":2,\"prefix\":\"pre2\"}]}"

        val res = fieldExtractor.extract(FieldExtractorRawExpression(expr), json)

        assertEquals("pre1", res)

    }

}