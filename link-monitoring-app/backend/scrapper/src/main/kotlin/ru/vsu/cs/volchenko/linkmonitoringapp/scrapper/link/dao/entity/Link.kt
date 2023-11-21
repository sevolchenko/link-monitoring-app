package ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.link.dao.entity

import com.fasterxml.jackson.databind.JsonNode
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant
import java.time.LocalDateTime

@Entity
@Table(name = "link")
class Link (
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:Column(name = "link_id")
    private val linkId: Long,

    @field:Column(name = "url", unique = true)
    private val url: String,

    @field:Column(name = "state", columnDefinition = "json")
    private val state: JsonNode,

    @Column(name = "last_scanned_at")
    private val lastScannedAt: LocalDateTime,

    @Column(name = "created_at")
    private val createdAt: LocalDateTime = LocalDateTime.now()
)
