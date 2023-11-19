package ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.service

import org.springframework.stereotype.Service
import ru.vsu.cs.volchenko.linkmonitoringapp.fieldextractor.FieldExtractor
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.LinkParser

@Service
class LinkService(
    private val linkParser: LinkParser,
    private val fieldExtractor: FieldExtractor
) {



}