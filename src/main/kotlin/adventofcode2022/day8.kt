package adventofcode2022

fun main() {
    val data = ClassLoader.getSystemResource("adventofcode2022/day8.txt").readText().lines()
    println(day8Solution1(data))
    println(day8Solution2(data))
}

fun day8Solution1(map: List<String>): Int {
    val rows = map.size
    val cols = map[0].length
    var count = 0

    for (r in 0 until rows) {
        for (c in 0 until cols) {
            if (r == 0 || r == rows - 1 || c == 0 || c == cols - 1) {
                count++
                continue
            }
            val current = map[r][c] - '0'
            var treeVisible = false
            val directions = listOf(Pair(1, 0), Pair(-1, 0), Pair(0, 1), Pair(0, -1))
            for (dir in directions) {
                var i = r + dir.first
                var j = c + dir.second
                while (i in 0 until rows && j in 0 until cols) {
                    if (map[i][j] - '0' >= current) {
                        break
                    }
                    i += dir.first
                    j += dir.second
                }
                if (i in 0 until rows && j in 0 until cols) {
                    continue
                }
                treeVisible = true
                break
            }
            if (treeVisible) {
                count++
            }
        }
    }

    return count
}

fun day8Solution2(map: List<String>): Int {
    var maxScore = 0
    for (i in map.indices) {
        for (j in map[0].indices) {
            val up = countTreesInDirection(map, i, j, -1, 0)
            val down = countTreesInDirection(map, i, j, 1, 0)
            val left = countTreesInDirection(map, i, j, 0, -1)
            val right = countTreesInDirection(map, i, j, 0, 1)
            maxScore = maxOf(maxScore, up * down * left * right)
        }
    }
    return maxScore
}


fun countTreesInDirection(map: List<String>, i: Int, j: Int, di: Int, dj: Int): Int {
    val currTree = map[i][j] - '0'
    var count = 0
    var k = i + di
    var l = j + dj
    while (k in map.indices && l in map[0].indices) {
        val nextTree = map[k][l] - '0'
        if (nextTree < currTree) {
            count++
            k += di
            l += dj
        } else {
            count++
            break
        }
    }
    return count
}

