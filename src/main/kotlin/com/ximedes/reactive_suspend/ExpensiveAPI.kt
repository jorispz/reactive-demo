package com.ximedes.reactive_suspend

import com.ximedes.api.ExpensiveAPISuspend
import com.ximedes.api.ExpensiveResponse
import com.ximedes.api.URI
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

@Component
class ExpensiveAPI : ExpensiveAPISuspend {
    private val webClient = WebClient.create()

    override suspend fun performExpensiveAPICall(duration: Long): ExpensiveResponse {
        val response = webClient.get().uri("$URI?sleep=${duration}").retrieve()
        return response.bodyToMono<ExpensiveResponse>().awaitSingle()
    }

}