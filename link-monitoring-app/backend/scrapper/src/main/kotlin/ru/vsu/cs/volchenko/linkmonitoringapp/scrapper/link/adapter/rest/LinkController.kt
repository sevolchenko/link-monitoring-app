package ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.link.adapter.rest

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.link.LinkService
import ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.link.adapter.rest.dto.AddLinkRequest

@RestController
@RequestMapping("/api/v1/links")
class LinkController(
        val linkService: LinkService
) {

    @PostMapping
    fun addLink(@RequestBody request: AddLinkRequest) {

        linkService.addLink(request)

    }

}
