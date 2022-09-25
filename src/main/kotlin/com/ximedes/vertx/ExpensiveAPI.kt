package com.ximedes.vertx

import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.ximedes.api.ExpensiveAPIVertX
import com.ximedes.api.ExpensiveResponse
import com.ximedes.api.URI
import io.vertx.core.Future
import io.vertx.core.Vertx
import io.vertx.core.json.jackson.DatabindCodec
import io.vertx.ext.web.client.WebClient
import io.vertx.ext.web.codec.BodyCodec

class ExpensiveAPI(val vertx: Vertx) : ExpensiveAPIVertX {
    private val client = WebClient.create(vertx)

    init {
        DatabindCodec.mapper().registerKotlinModule()
        DatabindCodec.prettyMapper().registerKotlinModule()
    }

    override fun performExpensiveCall(duration: Long): Future<ExpensiveResponse> {
        return client.getAbs("$URI?sleep=${duration}")
            .`as`(BodyCodec.json(ExpensiveResponse::class.java))
            .send()
            .map { it.body() }
    }
}