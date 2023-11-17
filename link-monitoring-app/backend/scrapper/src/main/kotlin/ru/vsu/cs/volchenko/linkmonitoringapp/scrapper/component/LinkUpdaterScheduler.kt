package ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.component

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.configuration.ScrapperConfiguration

@Component
class LinkUpdaterScheduler(
        val scrapperConfiguration: ScrapperConfiguration
) {

    @Scheduled(fixedDelayString = "#{@schedulerProperties.interval.toString()}")
    fun update() {
        //log.info("Checking for links updates")
        //linkUpdater.update()
    }

    companion object {

        // logger

    }

}
