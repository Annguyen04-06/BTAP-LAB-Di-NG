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
        1, 2 -> println("The number is 1 or 2")
        3, 4 -> println("The number is 3 or 4")
        5, 6 -> println("The number is 5 or 6")
        else -> println("Invalid number")
    }
}

fun printHello() {
    println("Hello!")
}

fun printBorder(symbol: String, width: Int) {
    for (i in 0 until width) {
        print(symbol)
    }
    println()
}

fun rollDice(): Int {
    return (1..6).random()
}

