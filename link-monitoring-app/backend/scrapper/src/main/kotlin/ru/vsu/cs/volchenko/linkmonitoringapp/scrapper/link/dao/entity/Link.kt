package ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.link.dao.entity

import com.fasterxml.jackson.databind.JsonNode
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.net.URI
import java.time.OffsetDateTime

@Entity
@Table(name = "link")
class Link (
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:Column(name = "link_id")
    var linkId: Long,

    @field:Column(name = "url", unique = true)
    var url: URI,

    @field:Column(name = "state", columnDefinition = "json")
    var state: JsonNode,

    @Column(name = "last_scanned_at")
    var lastScannedAt: OffsetDateTime,

    @Column(name = "created_at")
    var createdAt: OffsetDateTime = OffsetDateTime.now()
)
