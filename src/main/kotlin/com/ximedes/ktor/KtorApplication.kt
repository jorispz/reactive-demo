package com.ximedes.ktor

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {

    val api = ExpensiveAPI()

    routing {
        get("/") {
            val duration = call.request.queryParameters["sleep"]?.toLong() ?: 0
            val response = api.performExpensiveAPICall(duration)
            call.respondText { response.message }
        }
    }

}
