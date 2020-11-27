package com.ximedes.reactive

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import reactor.core.publisher.Mono
import java.time.Duration

@SpringBootApplication
class ReactiveApplication

fun main(args: Array<String>) {
	runApplication<ReactiveApplication>(*args)
}

@Controller("/")
class ReactiveController {

	@GetMapping
	fun sleep(@RequestParam sleep: Long): Mono<String> {
		return Mono.just("Hello").delayElement(Duration.ofMillis(sleep))
	}

}
