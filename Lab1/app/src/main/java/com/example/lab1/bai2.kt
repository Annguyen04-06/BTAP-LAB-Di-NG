package com.example.lab1
import kotlin.math.PI

abstract class Dwelling(protected var residents: Int) {
    abstract val buildingMaterial: String
    abstract fun floorArea(): Double
    fun hasRoom(): Boolean = residents < capacity
    open val capacity: Int = residents
}

open class RoundHut(residents: Int, private val radius: Double) : Dwelling(residents) {
    override val buildingMaterial: String = "Straw"
    override val capacity: Int = 4
    override fun floorArea(): Double = PI * radius * radius
}

class SquareCabin(residents: Int, private val length: Double) : Dwelling(residents) {
    override val buildingMaterial: String = "Wood"
    override val capacity: Int = 6
    override fun floorArea(): Double = length * length
}

fun main() {
    val numbers = listOf(1, 2, 3, 4, 5, 6)
    val first = numbers[0]
    val reversed = listOf("red", "blue", "green").reversed()

    val entrees = mutableListOf<String>()
    entrees.add("spaghetti")
    entrees[0] = "lasagna"
    entrees.remove("lasagna")

    for (element in numbers) {
        println(element)
    }

    var index = 0
    while (index < numbers.size) {
        println(numbers[index])
        index++
    }

    val name = "Android"
    println(name.length)

    val number = 10
    val groups = 5
    println("$number people")
    println("${number * groups} people")

    var a = 10
    val b = 5
    a += b
    a -= b
    a *= b
    a /= b

    val squareCabin = SquareCabin(3, 5.0)
    with(squareCabin) {
        println("Capacity: ${capacity}")
        println("Material: ${buildingMaterial}")
        println("Has room? ${hasRoom()}")
        println("Floor area: ${floorArea()}")
    }
}
