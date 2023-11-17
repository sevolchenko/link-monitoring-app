package ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.configuration

import jakarta.validation.Valid
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.validation.annotation.Validated
import ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.configuration.property.LinkSourceProperties
import ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.configuration.property.SchedulerProperties
import java.time.Duration

@Validated
@EnableScheduling
@ConfigurationProperties(prefix = "scrapper")
class ScrapperConfiguration(
        val scheduler: SchedulerProperties,
        val linkCheckDelay: Duration,
        @field:Valid
        val sources: List<LinkSourceProperties>
) {

        @Bean
        fun schedulerProperties() = scheduler

}