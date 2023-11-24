package ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.link

import org.springframework.stereotype.Service
import ru.vsu.cs.volchenko.linkmonitoringapp.fieldextractor.FieldExtractor
import ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.LinkParser
import ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.link.dao.LinkDao

@Service
@Suppress("UnusedPrivateProperty", "EmptyClassBlock")
class LinkService(
    val dao: LinkDao
)
