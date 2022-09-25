package com.ximedes.completablefuture

import com.ximedes.api.ExpensiveAPICompletableFuture
import com.ximedes.api.ExpensiveResponse
import com.ximedes.api.URI
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import java.util.concurrent.CompletableFuture

@Component
class ExpensiveAPI : ExpensiveAPICompletableFuture {
    private val webClient = WebClient.create()

    override fun performExpensiveAPICall(duration: Long): CompletableFuture<ExpensiveResponse> {
        val response = webClient.get().uri("$URI?sleep=${duration}").retrieve()
        return response.bodyToMono<ExpensiveResponse>().toFuture()
    }
}