package com.ximedes.reactive_suspend

import kotlinx.coroutines.delay
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
class ReactiveWithSuspendFunController {

    @GetMapping
    suspend fun sleep(@RequestParam sleep: Long): String {
        delay(sleep)
        return "Hello"
    }

}
