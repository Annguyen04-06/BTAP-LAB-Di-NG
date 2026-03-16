package com.example.lab1

fun main() {

    println("Hello, world!")
    println("This is the text to print!")

    val age = "5"
    val name = "Rover"

    var roll = 6
    var rolledValue: Int = 4

    println("You are already ${age}!")
    println("You are already ${age} days old, ${name}!")

    printHello()

    printBorder("*", 10)

    val diceNumber = rollDice()
    println("Dice rolled: $diceNumber")

    if (diceNumber > 3) {
        println("The number is greater than 3")
    } else {
        println("The number is 3 or less")
    }

    when (diceNumber) {
        1 -> println("You rolled 1")
        2 -> println("You rolled 2")
        3 -> println("You rolled 3")
        4 -> println("You rolled 4")
        5 -> println("You rolled 5")
        else -> println("You rolled 6")
    }

    val myFirstDice = Dice(6)
    val result = myFirstDice.roll()
    println("Result from Dice class: $result")
}

fun printHello() {
    println("Hello Kotlin")
}

fun printBorder(border: String, timesToRepeat: Int) {
    repeat(timesToRepeat) {
        print(border)
    }
    println()
}

fun rollDice(): Int {
    val randomNumber = (1..6).random()
    return randomNumber
}

class Dice(val numSides: Int) {

    fun roll(): Int {
        val randomNumber = (1..numSides).random()
        return randomNumber
    }
}
