package com.ximedes.blocking

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@SpringBootApplication
class BlockingApplication

fun main(args: Array<String>) {
    runApplication<BlockingApplication>(*args)
}

@Controller("/")
class BlockingController {

    @GetMapping
    fun sleep(@RequestParam sleep: Long): ResponseEntity<String> {
        Thread.sleep(sleep)
        return ResponseEntity("Hello", HttpStatus.OK)
    }

}