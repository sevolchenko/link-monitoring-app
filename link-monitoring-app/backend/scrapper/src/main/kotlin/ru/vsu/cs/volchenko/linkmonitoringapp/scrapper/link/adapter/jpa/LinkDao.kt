package ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.link.adapter.jpa

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.link.adapter.jpa.entity.Link
import java.time.OffsetDateTime

@Repository
interface LinkDao : JpaRepository<Link, Int> {
    fun findAllByLastScannedAtBefore(time: OffsetDateTime) : List<Link>

}
