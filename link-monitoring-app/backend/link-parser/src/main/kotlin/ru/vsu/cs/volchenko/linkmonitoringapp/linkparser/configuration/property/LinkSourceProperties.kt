package ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.configuration.property

import org.springframework.boot.context.properties.ConfigurationProperties

data class LinkSourceProperties(
    var name: String,
    var linkTemplate: LinkTemplate,
    var api: ApiProperties,
)
