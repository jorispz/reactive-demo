package com.ximedes.api

import io.vertx.core.Future
import reactor.core.publisher.Mono
import java.util.concurrent.CompletableFuture

const val URI = "https://3q2l17vb35.execute-api.eu-central-1.amazonaws.com/live/sleep"
const val USE_AWS = true;

data class ExpensiveResponse(val message: String)

interface ExpensiveAPISync {
    fun performExpensiveCall(duration: Long): ExpensiveResponse
}

interface ExpensiveAPIVertX {
    fun performExpensiveCall(duration: Long): Future<ExpensiveResponse>
}

interface ExpensiveAPICompletableFuture {
    fun performExpensiveAPICall(duration: Long): CompletableFuture<ExpensiveResponse>
}

interface ExpensiveAPIReactive {
    fun performExpensiveAPICall(duration: Long): Mono<ExpensiveResponse>
}

interface ExpensiveAPISuspend {
    suspend fun performExpensiveAPICall(duration: Long): ExpensiveResponse
}

