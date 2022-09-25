package com.ximedes.blocking

import com.ximedes.api.ExpensiveAPISync
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class BlockingApplication

fun main(args: Array<String>) {
    runApplication<BlockingApplication>(*args)
}

@RestController("/")
class BlockingController(val api: ExpensiveAPISync) {

    @GetMapping
    fun sleep(@RequestParam sleep: Long): String {
        val response = api.performExpensiveCall(sleep)
        return response.message
    }

}

