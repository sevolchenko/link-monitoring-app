package ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.dispatcher

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class KafkaEventsProducer {

    @Suppress("EmptyMethodBlock")
    fun sendEvent(event: String){

        log.info("Event detekted: $event")

    }

    companion object {
        private val log = LoggerFactory.getLogger(KafkaEventsProducer::class.java)
    }

}