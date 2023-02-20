package adventofcode2022

data class Move(val numCrates: Int, val fromStackIndex: Int, val toStackIndex: Int)

fun main() {
    val data = ClassLoader.getSystemResource("adventofcode2022/day5.txt").readText()
    val (startingStacks, moves) = day5ParseInput(data)
    println(day5Solution1(startingStacks, moves))
    println(day5Solution2(startingStacks, moves))
}

fun parseMove(moveString: String): Move {
    val parts = moveString.split(" ")
    val numCrates = parts[1].toInt()
    val fromStackIndex = parts[3].toInt() - 1
    val toStackIndex = parts[5].toInt() - 1
    return Move(numCrates, fromStackIndex, toStackIndex)
}

fun day5ParseInput(input: String): Pair<List<List<String>>, List<Move>> {
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
    val moves = lines.filter { it.contains("move") }.map { parseMove(it) }
    return Pair(startingStacks, moves)
}

fun day5Solution1(startingStacks: List<List<String>>, moves: List<Move>): String {
    val stacks = startingStacks.map { it.toCollection(ArrayDeque()) }

    for (move in moves) {
        repeat(move.numCrates) {
            val crate = stacks[move.fromStackIndex].removeLast()
            stacks[move.toStackIndex].addLast(crate)
        }
    }

    return stacks.map { it.last() }.joinToString("")
}

fun day5Solution2(startingStacks: List<List<String>>, moves: List<Move>): String {
    val stacks = startingStacks.map { it.toCollection(ArrayDeque()) }

    for (move in moves) {
        val crates = mutableListOf<String>()
        repeat(move.numCrates) {
            crates.add(stacks[move.fromStackIndex].removeLast())
        }
        crates.reverse()
        stacks[move.toStackIndex].addAll(crates)
    }

    return stacks.map { it.last() }.joinToString("")
}