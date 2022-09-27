package com.ximedes.basic

import com.ximedes.api.URI
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors
import kotlin.concurrent.thread
import kotlin.coroutines.resume

val client = HttpClient.newBuilder().build()

fun makeAPICall(duration: Long): String {
    val request = HttpRequest.newBuilder().uri(URI("${URI}?sleep=$duration")).GET().build()
    val response = client.send(request, BodyHandlers.ofString())
    return response.body()
}

fun makeAPICallAsync(duration: Long): CompletableFuture<String> {
    val request = HttpRequest.newBuilder().uri(URI("${URI}?sleep=$duration")).GET().build()
    val response = client.sendAsync(request, BodyHandlers.ofString())
    return response.thenApply { it.body() }
}

fun makeAPICallCallback(duration: Long, callback: (String) -> Unit) {
    val request = HttpRequest.newBuilder().uri(URI("${URI}?sleep=$duration")).GET().build()
    val response = client.sendAsync(request, BodyHandlers.ofString())
    response.thenApply { callback(it.body()) }
}

suspend fun makeAPICallSuspend(duration: Long): String = suspendCancellableCoroutine { continuation ->
    val request = HttpRequest.newBuilder().uri(URI("${URI}?sleep=$duration")).GET().build()
    val response = client.sendAsync(request, BodyHandlers.ofString())
    response.thenApply { continuation.resume(it.body()) }
}


fun main_basic() {
    println("Starting")
    val response = makeAPICall(1500)
    println(response)
    println("Done")
}

fun main() {
    runBlocking {
        println("Starting")
        val spinner = launch {
            while (true) {
                print(".")
                delay(100)
            }
        }
        val response = makeAPICallSuspend(2000)
        spinner.cancel()
        println()
        println(response)
        println("Done")
    }

}


fun main_callback() {
    println("Starting")
    var running = true

    val response = makeAPICallCallback(1500) {
        running = false;
        println()
        println(it)
    }

    while (running) {
        print(".")
        Thread.sleep(100)
    }
    println("Done")
}

fun main_thread() {
    println("Starting")
    val thread = thread {
        try {
            while (true) {
                print(".")
                Thread.sleep(100)
            }
        } catch (_: InterruptedException) {
        }
    }
    val response = makeAPICall(1500)
    thread.interrupt()
    println()
    println(response)
    println("Done")
}

fun main_executor() {
    val executor = Executors.newSingleThreadExecutor()
    println("Starting")
    val task = executor.submit {
        while (true) {
            print("*")
            Thread.sleep(100)
        }
    }
    val response = makeAPICall(1500)
    task.cancel(true)
    println()
    println(response)
    println("Done")
    executor.shutdown()
}

fun main_future() {
    println("Starting")
    var running = true
    val response = makeAPICallAsync(1500).thenApply {
        running = false;
        it
    }
    while (running) {
        print(".")
        Thread.sleep(100)
    }
    println()
    println(response.get())
    println("Done")
}


