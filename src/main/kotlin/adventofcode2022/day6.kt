package adventofcode2022

fun main() {
    val data = ClassLoader.getSystemResource("adventofcode2022/day6.txt").readText()
    println(day6Solution(data, 4))
    println(day6Solution(data, 14))
}

fun day6Solution(datastream: String, numDistinct: Int): Int {
    var chunk = datastream.take(numDistinct)
    var index = 0

    while (chunk.length == numDistinct) {
        if (chunk.toSet().size == numDistinct) {
            return index + numDistinct
        }

        index++
        chunk = datastream.drop(index).take(numDistinct)
    }

    return -1
}