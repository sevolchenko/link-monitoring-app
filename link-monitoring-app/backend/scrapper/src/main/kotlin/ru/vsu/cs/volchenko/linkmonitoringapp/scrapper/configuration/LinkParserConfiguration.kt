package ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.configuration.RequiredLinkParserConfiguration
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.model.LinkTemplate

@Configuration
class LinkParserConfiguration(
        val config: ScrapperConfiguration
) : RequiredLinkParserConfiguration {

    @Bean
    override fun linkTemplates() : List<LinkTemplate> {
        return config.sources.map { it.linkTemplate }
    }



}