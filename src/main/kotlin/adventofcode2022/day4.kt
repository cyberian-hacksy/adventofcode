package adventofcode2022

fun main() {
    val data = ClassLoader.getSystemResource("adventofcode2022/day4.txt").readText().lines()
    day4Solution(data, 1)
    day4Solution(data, 2)
}

fun day4Solution(sections: List<String>, solutionType: Int) {
    var count = 0
    for (assignment in sections) {
        val ranges = assignment.split(",")
        val range1 = ranges[0].split("-").map { it.toInt() }
        val range2 = ranges[1].split("-").map { it.toInt() }
        if (solutionType == 1) {
            if ((range1[0] >= range2[0]) && (range1[1] <= range2[1])) {
                count++
            } else if ((range1[0] <= range2[0]) && (range1[1] >= range2[1])) {
                count++
            }
        } else if (solutionType == 2) {
            if ((range1[0]..range1[1]).intersect(range2[0]..range2[1]).isNotEmpty()) {
                count++
            }
        }
    }
    println(count)
}