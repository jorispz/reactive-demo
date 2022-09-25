package com.ximedes.vertx

import io.vertx.core.Vertx

fun main() {
    val vertx = Vertx.vertx()
    val server = vertx.createHttpServer()
    val api = ExpensiveAPI(vertx)

    server.requestHandler { request ->
        val duration = request.getParam("sleep").toLong()
        api.performExpensiveCall(duration)
            .onSuccess { apiResponse ->
                request.response().end(apiResponse.message)
            }.onFailure {
                println(it.message)
            }
    }

    server.listen(8080)

}