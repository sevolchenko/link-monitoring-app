package ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.link.adapter

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.link.LinkService

@RestController
@RequestMapping("/api/v1")
@Suppress("UnusedPrivateProperty", "EmptyClassBlock")
class LinkController(
        val linkService: LinkService
)
