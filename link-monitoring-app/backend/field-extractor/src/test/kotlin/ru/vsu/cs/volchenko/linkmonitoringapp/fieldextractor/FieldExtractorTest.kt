package ru.vsu.cs.volchenko.linkmonitoringapp.fieldextractor

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.vsu.cs.volchenko.linkmonitoringapp.fieldextractor.model.FieldExtractorRawExpression

class FieldExtractorTest {

    val fieldExtractor = FieldExtractor()

    val objectMapper = ObjectMapper()
            .registerModule(KotlinModule.Builder()
                    .withReflectionCacheSize(512)
                    .configure(KotlinFeature.NullToEmptyCollection, false)
                    .configure(KotlinFeature.NullToEmptyMap, false)
                    .configure(KotlinFeature.NullIsSameAsDefault, false)
                    .configure(KotlinFeature.SingletonSupport, false)
                    .configure(KotlinFeature.StrictNullChecks, false)
                    .build())

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
    fun `happy path array lentgh`() {

        val expr = "$.items.\$item1"
        val json = objectMapper.writeValueAsString(
                object {
                    val items = listOf("first", "second", "third")
                }
        )

        val res = fieldExtractor.extract(FieldExtractorRawExpression(expr), json)

        assertEquals("second", res)

    }

}