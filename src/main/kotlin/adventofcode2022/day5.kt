package adventofcode2022

fun main() {
    val data = ClassLoader.getSystemResource("adventofcode2022/day5.txt").readText()
    val (startingStacks, moves) = day5ParseInput(data)
    println(day5Solution1(startingStacks, moves))
    println(day5Solution2(startingStacks, moves))
}

fun day5ParseInput(input: String): Pair<List<List<String>>, List<String>> {
    val lines = input.lines().filter { it.isNotBlank() }
    val startingStacks = mutableListOf<MutableList<String>>()
    val stackData = lines.filter { it.contains("[") }
        .map {
            it.toList().chunked(4)
                .map { it.joinToString("") }
                .map { it.trim().replace("[", "").replace("]", "") }
        }.reversed()
    stackData.forEach {
        it.forEachIndexed { index, s ->
            if (startingStacks.size < index + 1) startingStacks.add(mutableListOf())
            if (s.isNotBlank()) startingStacks[index].add(s)
        }
    }
    val moves = lines.filter { it.contains("move") }
    return Pair(startingStacks, moves)
}

fun day5Solution1(startingStacks: List<List<String>>, moves: List<String>): String {
    val stacks = startingStacks.map { it.toCollection(ArrayDeque()) }

    for (move in moves) {
        val parts = move.split(" ")
        val numCrates = parts[1].toInt()
        val fromStackIndex = parts[3].toInt() - 1
        val toStackIndex = parts[5].toInt() - 1
        repeat(numCrates) {
            val crate = stacks[fromStackIndex].removeLast()
            stacks[toStackIndex].addLast(crate)
        }
    }

    return stacks.map { it.last() }.joinToString("")
}

fun day5Solution2(startingStacks: List<List<String>>, moves: List<String>): String {
    val stacks = startingStacks.map { it.toCollection(ArrayDeque()) }

    for (move in moves) {
        val parts = move.split(" ")
        val numCrates = parts[1].toInt()
        val fromStackIndex = parts[3].toInt() - 1
        val toStackIndex = parts[5].toInt() - 1

        val crates = mutableListOf<String>()
        repeat(numCrates) {
            crates.add(stacks[fromStackIndex].removeLast())
        }
        crates.reverse()
        stacks[toStackIndex].addAll(crates)
    }

    return stacks.map { it.last() }.joinToString("")
}