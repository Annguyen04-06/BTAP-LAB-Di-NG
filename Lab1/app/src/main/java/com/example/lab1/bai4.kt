package com.example.lab1

import kotlinx.coroutines.*

suspend fun getValue(): Double {
    delay(500)
    return 9.5
}

suspend fun processValue(): Double {
    val value = getValue()
    return value * 2
}

object DataProviderManager {
    fun getName(): String = "DataProviderManager"
}

enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

fun main() = runBlocking {
    val job: Job = GlobalScope.launch {
        val output = getValue()
        println("Output is $output")
    }

    delay(200)
    job.cancel()

    val output2 = getValue()
    println("Output is $output2")

    val deferred: Deferred<Double> = async {
        getValue()
    }
    println("Output is ${deferred.await()}")

    try {
        val x = 10 / 0
        println(x)
    } catch (exception: Exception) {
        println("Error: ${exception.message}")
    }

    val direction = Direction.NORTH
    when (direction) {
        Direction.NORTH -> println("Go North")
        Direction.SOUTH -> println("Go South")
        Direction.WEST -> println("Go West")
        Direction.EAST -> println("Go East")
    }

    println(DataProviderManager.getName())
    println(processValue())
}
