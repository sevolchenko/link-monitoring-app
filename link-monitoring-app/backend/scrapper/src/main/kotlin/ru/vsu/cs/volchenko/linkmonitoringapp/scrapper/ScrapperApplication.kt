package ru.vsu.cs.volchenko.linkmonitoringapp.scrapper

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
@EnableConfigurationProperties
@ConfigurationPropertiesScan("ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.configuration")
class ScrapperApplication

fun main(args: Array<String>) {
    val ctx = SpringApplication.run(ScrapperApplication::class.java, *args)
    println()
}