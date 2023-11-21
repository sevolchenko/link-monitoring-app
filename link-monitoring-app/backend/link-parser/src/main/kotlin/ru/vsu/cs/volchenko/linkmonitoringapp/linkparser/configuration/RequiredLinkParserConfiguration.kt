package ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.configuration

import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.model.LinkTemplate

interface RequiredLinkParserConfiguration {

    fun linkTemplates() : List<LinkTemplate>

}
