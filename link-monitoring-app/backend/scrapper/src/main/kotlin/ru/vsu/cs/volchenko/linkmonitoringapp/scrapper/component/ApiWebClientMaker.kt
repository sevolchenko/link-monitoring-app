package ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.component

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.ClientRequest
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.configuration.property.ApiProperties
import java.net.URI

@Component
class ApiWebClientMaker {

    fun make(properties: ApiProperties) : WebClient {
        return WebClient.builder()
                .baseUrl(properties.host.toString())
                .defaultHeaders { headers ->
                    properties.defaultParams.headers.forEach {
                        headers.add(it.key, it.value)
                    }
                }
                .filter(ExchangeFilterFunction.ofRequestProcessor { clientRequest ->
                    val newUri = clientRequest.url().toString() +
                            properties.defaultParams.query.map {
                                "${it.key}=${it.value}"
                            }.joinToString("&", prefix="?")
                    val newRequest = ClientRequest.from(clientRequest).url(URI.create(newUri)).build()
                    Mono.just(newRequest)
                })
                .build()
    }

}