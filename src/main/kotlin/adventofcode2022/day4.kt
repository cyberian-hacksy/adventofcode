package adventofcode2022

fun main() {
    val data = ClassLoader.getSystemResource("adventofcode2022/day4.txt").readText().lines()
    day4Solution1(data)
    day4Solution2(data)
}

fun day4Solution1(sections: List<String>) {
    var count = 0
    for (assignment in sections) {
        val ranges = assignment.split(",")
        val range1 = ranges[0].split("-").map { it.toInt() }
        val range2 = ranges[1].split("-").map { it.toInt() }
        if ((range1[0] >= range2[0]) && (range1[1] <= range2[1])) {
            count++
        } else if ((range1[0] <= range2[0]) && (range1[1] >= range2[1])) {
            count++
        }
    }
    println(count)
}

fun day4Solution2(sections: List<String>) {
    var count = 0
    for (assignment in sections) {
        val ranges = assignment.split(",")
        val range1 = ranges[0].split("-").map { it.toInt() }
        val range2 = ranges[1].split("-").map { it.toInt() }
        if ((range1[0]..range1[1]).intersect(range2[0]..range2[1]).isNotEmpty()) {
            count++
        }
    }
    println(count)
}

