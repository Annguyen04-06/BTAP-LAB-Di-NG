package com.example.lab1

fun main() {
    val numbers = listOf(0, 3, 8, 4, 0, 5, 5, 8, 9, 2)
    val setOfNumbers = numbers.toSet()
    println(setOfNumbers)

    val set1 = setOf(1, 2, 3)
    val set2 = mutableSetOf(3, 4, 5)

    println(set1.intersect(set2))
    println(set1.union(set2))

    val peopleAges = mutableMapOf<String, Int>(
        "Fred" to 30,
        "Ann" to 23
    )

    peopleAges.put("Barbara", 42)
    peopleAges["Joe"] = 51

    peopleAges.forEach { print("${it.key} is ${it.value}, ") }
    println()

    println(peopleAges.map { "${it.key} is ${it.value}" }.joinToString(", "))

    val filteredNames = peopleAges.filter { it.key.length < 4 }
    println(filteredNames)

    val words = listOf("about", "acute", "balloon", "best", "brief", "class")
    val filteredWords = words.filter { it.startsWith("b", ignoreCase = true) }
        .shuffled()
        .take(2)
        .sorted()
    println(filteredWords)

    val arguments: BundleFake? = BundleFake(mapOf("letter" to "A"))
    var letterId = ""
    arguments?.let {
        letterId = it.getString(DetailActivity.LETTER).toString()
    }
    println(letterId)

    val binding: BindingFake? = BindingFake()
    binding?.apply {
        flavorFragment = this
    }

    val intent: IntentFake? = IntentFake(BundleFake(mapOf("letter" to "B")))
    val safeLetterId = intent?.extras?.getString("letter").toString()
    println(safeLetterId)

    val triple: (Int) -> Int = { a: Int -> a * 3 }
    println(triple(5))

    var quantity: Int? = null
    println(quantity ?: 0)
    quantity = 4
    println(quantity ?: 0)
}

private var _currentScrambledWord = "test"
val currentScrambledWord: String
    get() = _currentScrambledWord

private var wordsList: MutableList<String> = mutableListOf()
private lateinit var currentWord: String

class DetailActivity {
    companion object {
        const val LETTER = "letter"
    }
}

class BundleFake(private val data: Map<String, String>) {
    fun getString(key: String): String? = data[key]
}

class IntentFake(val extras: BundleFake?)

class BindingFake {
    var flavorFragment: Any? = null
}

class GameViewModel

fun viewModels(): Lazy<GameViewModel> {
    return lazy { GameViewModel() }
}

private val viewModel: GameViewModel by viewModels()
