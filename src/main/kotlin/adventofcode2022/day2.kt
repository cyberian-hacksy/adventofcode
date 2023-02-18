package adventofcode2022


fun main() {
    val data = ClassLoader.getSystemResource("adventofcode2022/day2.txt").readText().lines()
        .map { line ->
            val parts = line.split(" ")
            parts[0] to parts[1]
        }.toList()
    solution(data)
    val choiceMap = mapOf(
        "A" to mapOf("X" to "Z", "Y" to "X", "Z" to "Y"),
        "B" to mapOf("X" to "X", "Y" to "Y", "Z" to "Z"),
        "C" to mapOf("X" to "Y", "Y" to "Z", "Z" to "X")
    )
    solution(data.map { Pair(it.first, choiceMap.getValue(it.first).getValue(it.second)) })
}

fun solution(strategy: List<Pair<String, String>>) {
    val score = strategy.sumOf { (opponentChoice, yourChoice) ->
        val outcomes = mapOf(
            "A" to mapOf("X" to 3, "Y" to 6, "Z" to 0),
            "B" to mapOf("X" to 0, "Y" to 3, "Z" to 6),
            "C" to mapOf("X" to 6, "Y" to 0, "Z" to 3)
        ).getValue(opponentChoice)[yourChoice]
        mapOf("X" to 1, "Y" to 2, "Z" to 3).getValue(yourChoice) + outcomes!!
    }
    println("Total Score: $score")
}