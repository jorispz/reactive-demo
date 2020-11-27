package com.ximedes.reactive_suspend

import kotlinx.coroutines.delay
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@SpringBootApplication
class ReactiveWithSuspendFunApplication

fun main(args: Array<String>) {
    runApplication<ReactiveWithSuspendFunApplication>(*args)
}

@Controller("/")
class ReactiveWithSuspendFunController {

    @GetMapping
    suspend fun sleep(@RequestParam sleep: Long): String {
        delay(sleep)
        return "Hello"
    }

}
