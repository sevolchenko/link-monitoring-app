package ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.component

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.configuration.ScrapperConfiguration

@Component
class LinkUpdaterScheduler(
        val linkUpdateChecker: LinkUpdateChecker
) {

    @Scheduled(fixedDelayString = "#{@schedulerProperties.interval.toString()}")
    fun update() {
        log.info("Checking for links updates")
        linkUpdateChecker.check()
    }

    companion object {
        private val log = LoggerFactory.getLogger(LinkUpdaterScheduler::class.java)
    }

}
