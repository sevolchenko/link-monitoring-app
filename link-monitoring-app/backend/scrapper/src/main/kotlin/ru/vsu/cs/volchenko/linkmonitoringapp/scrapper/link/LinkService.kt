package ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.link

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.link.adapter.jpa.LinkDao
import ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.link.adapter.rest.dto.AddLinkRequest
import java.util.*

@Service
class LinkService(
    val dao: LinkDao
) {

    @Transactional
    fun addLink(request: AddLinkRequest) {

        System.arraycopy()

        dao.save()

    }

    sealed class LinkServiceResult {

        data class Added(): LinkServiceResult()
        data class

    }

}
