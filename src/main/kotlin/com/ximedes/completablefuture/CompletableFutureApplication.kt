package com.ximedes.completablefuture

import com.ximedes.api.ExpensiveAPICompletableFuture
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor


@SpringBootApplication
@EnableAsync
class CompletableFutureApplication {
    @Bean
    fun taskExecutor(): Executor {
        val executor = ThreadPoolTaskExecutor()
        executor.corePoolSize = 100
        executor.maxPoolSize = 100
        executor.setQueueCapacity(500)
        executor.setThreadNamePrefix("async-")
        executor.initialize()
        return executor
    }
}

fun main(args: Array<String>) {
    runApplication<CompletableFutureApplication>(*args)
}

@RestController("/")
class CompletableFutureController(val api: ExpensiveAPICompletableFuture) {

    @GetMapping
    @Async
    fun sleep(@RequestParam sleep: Long): CompletableFuture<String> {
        val future = api.performExpensiveAPICall(sleep)
        val response = future.thenApply { it.message }
        return response
    }

}
