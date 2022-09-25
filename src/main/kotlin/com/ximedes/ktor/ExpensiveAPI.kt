package com.ximedes.ktor

import com.ximedes.api.ExpensiveAPISuspend
import com.ximedes.api.ExpensiveResponse
import com.ximedes.api.URI
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.jackson.*

class ExpensiveAPI : ExpensiveAPISuspend {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            jackson()
        }
    }

    override suspend fun performExpensiveAPICall(duration: Long): ExpensiveResponse {
        return client.request("$URI?sleep=${duration}").body()
    }
}