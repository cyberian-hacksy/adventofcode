package adventofcode2022

fun main() {
    val data = ClassLoader.getSystemResource("adventofcode2022/day1.txt").readText().lines()
        .map { s -> if (!s.isEmpty()) s.toInt() else 0 }
    day1Solution(data, 1)
    day1Solution(data, 3)
}

fun day1Solution(calories: List<Int>, numElves: Int) {
    val elfCalories = mutableMapOf<Int, Int>()
    var elfNumber = 0

    for (calorie in calories) {
        elfCalories[elfNumber] = elfCalories.getOrDefault(elfNumber, 0) + calorie
        if (calorie == 0) elfNumber++
    }

    val maxCalories =
        elfCalories.toList().sortedByDescending { (_, value) -> value }.take(numElves).map { (_, value) -> value }.sum()
    println(maxCalories)
}