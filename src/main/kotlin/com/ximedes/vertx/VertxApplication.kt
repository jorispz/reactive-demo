package com.ximedes.vertx

import io.vertx.core.Vertx

fun main() {
    val vertx = Vertx.vertx()
    val server = vertx.createHttpServer()

    server.requestHandler { request ->
        val ms = request.getParam("sleep").toLong()
        vertx.setTimer(ms) {
            val response = request.response()
            response.end("Hello")
        }
    }

    server.listen(8080)
}