package adventofcode2022

fun main() {
    val data = ClassLoader.getSystemResource("adventofcode2022/day1.txt").readText().lines()
        .map { s -> if (!s.isEmpty()) s.toInt() else 0 }
    day1Solution1(data)
    day1Solution2(data)
}

fun day1Solution1(calories: List<Int>) {
    var maxCalories = 0
    var currentElf = 0
    var currentCalories = 0

    for (i in calories.indices) {
        if (calories[i] > 0) {
            currentCalories += calories[i]
        } else {
            if (currentCalories > maxCalories) {
                maxCalories = currentCalories
            }
            currentElf++
            currentCalories = 0
        }
    }

    if (currentCalories > maxCalories) {
        maxCalories = currentCalories
    }

    println(maxCalories)
}

fun day1Solution2(calories: List<Int>) {
    val elfCalories = mutableMapOf<Int, Int>()
    var elfNumber = 0

    for (calorie in calories) {
        elfCalories[elfNumber] = elfCalories.getOrDefault(elfNumber, 0) + calorie
        if (calorie == 0) elfNumber++
    }

    println(elfCalories.toList().sortedByDescending { (_, value) -> value }.take(3).map { (_, value) -> value }.sum())
}

