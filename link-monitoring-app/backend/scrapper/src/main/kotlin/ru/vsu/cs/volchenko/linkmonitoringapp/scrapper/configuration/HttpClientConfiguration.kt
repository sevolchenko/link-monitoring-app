package ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.configuration

import io.netty.channel.ChannelOption
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ClientHttpConnector
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import reactor.netty.http.client.HttpClient

@Configuration
class HttpClientConfiguration {

    @Bean
    fun httpClient(): HttpClient {
        return HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
    }

    @Bean
    fun clientHttpConnector(httpClient: HttpClient): ClientHttpConnector {
        return ReactorClientHttpConnector(httpClient)
    }

}