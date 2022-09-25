package com.ximedes.reactive

import com.ximedes.api.ExpensiveAPIReactive
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@SpringBootApplication
class ReactiveApplication

fun main(args: Array<String>) {
	runApplication<ReactiveApplication>(*args)
}

@RestController("/")
class ReactiveController(val api: ExpensiveAPIReactive) {

	@GetMapping
	fun sleep(@RequestParam sleep: Long): Mono<String> {
		val response = api.performExpensiveAPICall(sleep)
		return response.map { it.message }
	}

}
