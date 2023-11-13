package ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.configuration.property.LinkSourceProperties

@ConfigurationProperties(prefix = "link-parser")
class LinkParserConfiguration(
    var sources: List<LinkSourceProperties>
) {

}