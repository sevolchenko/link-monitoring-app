package ru.vsu.cs.volchenko.linkmonitoringapp.scrapper

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.ComponentScan
import ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.configuration.ScrapperConfiguration

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
@EnableConfigurationProperties(ScrapperConfiguration::class)
@ComponentScan(basePackages = ["ru.vsu.cs.volchenko.linkmonitoringapp"])
class ScrapperApplication

fun main(args: Array<String>) {
    val ctx = SpringApplication.run(ScrapperApplication::class.java, *args)
    println()
}