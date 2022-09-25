package com.ximedes.blocking

import com.ximedes.api.ExpensiveAPISync
import com.ximedes.api.ExpensiveResponse
import com.ximedes.api.URI
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

@Component
class ExpensiveAPI : ExpensiveAPISync {
    private val webClient = WebClient.create()

    override fun performExpensiveCall(duration: Long): ExpensiveResponse {
        val response = webClient.get().uri("${URI}?sleep=${duration}").retrieve()
        val body = response.bodyToMono<ExpensiveResponse>().block()
        return body ?: throw Exception("Null response")
    }
}
