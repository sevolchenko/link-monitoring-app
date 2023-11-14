package ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.service

import jakarta.validation.Valid
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.configuration.LinkParserConfiguration

@Service
@Validated
class LinkService(
    @field:Valid val sources: LinkParserConfiguration
) {

}