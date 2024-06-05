package ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.component

import org.springframework.http.MediaType
import org.springframework.http.client.reactive.ClientHttpConnector
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.ClientRequest
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import reactor.netty.http.client.HttpClient
import ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.configuration.property.ApiProperties
import java.net.URI

@Component
class ApiWebClientMaker(
       val clientHttpConnector: ClientHttpConnector
) {

    fun make(properties: ApiProperties) : WebClient {
        return WebClient.builder()
                .baseUrl(properties.host.toString())
                .defaultHeaders { headers ->
                    properties.defaultParams.headers.forEach {
                        headers.add(it.key, it.value)
                    }
                    headers.accept = listOf(MediaType.APPLICATION_JSON)
                }
                .defaultUriVariables(properties.defaultParams.query)
                // TODO add Status Handler
                .clientConnector(clientHttpConnector)
                .build()
    }

}