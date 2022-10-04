package com.ximedes.blocking

import com.ximedes.api.ExpensiveAPISync
import com.ximedes.api.USE_AWS
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import kotlin.system.measureTimeMillis

@SpringBootApplication
class BlockingApplication

fun main(args: Array<String>) {
    runApplication<BlockingApplication>(*args)
}

@RestController("/")
class BlockingController(val api: ExpensiveAPISync) {

    @GetMapping
    fun sleep(@RequestParam sleep: Long): String {

        val response = if (USE_AWS) {
            api.performExpensiveCall(sleep).message
        } else {
            val delay = measureTimeMillis {
                Thread.sleep(sleep)
            }
            "Waited for $delay ms"
        }
        return response
    }

}

