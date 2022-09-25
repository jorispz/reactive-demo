package com.ximedes.reactive

import com.ximedes.api.ExpensiveAPIReactive
import com.ximedes.api.ExpensiveResponse
import com.ximedes.api.URI
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono

@Component
class ExpensiveAPI : ExpensiveAPIReactive {
    private val webClient = WebClient.create()

    override fun performExpensiveAPICall(duration: Long): Mono<ExpensiveResponse> {
        val response = webClient.get().uri("$URI?sleep=${duration}").retrieve()
        return response.bodyToMono()
    }
}