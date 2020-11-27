package com.ximedes.ktor

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.delay

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {

    routing {
        get("/") {
            val ms = call.request.queryParameters["sleep"]?.toLong() ?: 0
            delay(ms)
            call.respondText { "Hello" }
        }
    }

}