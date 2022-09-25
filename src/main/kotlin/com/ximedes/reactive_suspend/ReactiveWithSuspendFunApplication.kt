package com.ximedes.reactive_suspend

import com.ximedes.api.ExpensiveAPISuspend
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class ReactiveWithSuspendFunApplication

fun main(args: Array<String>) {
    runApplication<ReactiveWithSuspendFunApplication>(*args)
}

@RestController("/")
class ReactiveWithSuspendFunController(val api: ExpensiveAPISuspend) {

    @GetMapping
    suspend fun sleep(@RequestParam sleep: Long): String {
        val response = api.performExpensiveAPICall(sleep)
        return response.message
    }

}
