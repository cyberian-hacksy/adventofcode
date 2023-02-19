package adventofcode2022

fun main() {
    val data = ClassLoader.getSystemResource("adventofcode2022/day3.txt").readText().lines()
    day3Solution1(data)
    day3Solution2(data)
}

fun day3Solution1(items: List<String>) {
    var sum = 0
    for (item in items) {
        val firstHalf = item.substring(0, item.length / 2)
        val secondHalf = item.substring(item.length / 2)
        for (c in firstHalf) {
            if (secondHalf.contains(c)) {
                sum += if (c.isLowerCase()) {
                    c.code - 'a'.code + 1
                } else {
                    c.code - 'A'.code + 27
                }
                break
            }
        }
    }
    println(sum)
}

fun day3Solution2(items: List<String>) {
    var sum = 0
    for (i in items.indices step 3) {
        val group = items.subList(i, i + 3)
        val firstRucksack = group[0]
        for (c in firstRucksack) {
            if (group.all { it.contains(c) }) {
                sum += if (c.isLowerCase()) {
                    c.code - 'a'.code + 1
                } else {
                    c.code - 'A'.code + 27
                }
                break
            }
        }
    }
    println(sum)
}