package ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.configuration

import jakarta.validation.Valid
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.validation.annotation.Validated
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.configuration.property.LinkSourceProperties

@Validated
@ConfigurationProperties(prefix = "link-parser")
class LinkParserConfiguration(
    @field:Valid
    var sources: List<LinkSourceProperties>
) {

}