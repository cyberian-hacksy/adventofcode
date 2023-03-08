package adventofcode2022

fun main() {
    val data = ClassLoader.getSystemResource("adventofcode2022/day7.txt").readText().lines()
    day7Solution(data)
}

fun calculateDirectorySize(
    directories: MutableMap<String, MutableList<String>>,
    directory: String,
    directorySizes: MutableMap<String, Int>
): Int {
    if (directorySizes.containsKey(directory)) {
        return directorySizes[directory]!!
    }

    var size = 0
    val contents = directories[directory]!!
    for (content in contents) {
        val parts = content.split(" ")
        if (parts[0] == "dir") {
            val subDirectory = "$directory/${parts[1]}"
            size += calculateDirectorySize(directories, subDirectory, directorySizes)
        } else {
            size += parts[0].toInt()
        }
    }
    directorySizes[directory] = size
    return size
}

private fun day7Solution(data: List<String>) {
    // parse the input and build a map of directories and their contents
    val directories = mutableMapOf<String, MutableList<String>>()
    var currentDirectory = "/"
    var contents = mutableListOf<String>()
    for (line in data) {
        if (!line.startsWith("$")) {
            val parts = line.split(" ")
            if (parts[0] == "dir") {
                contents.add(line)
            } else {
                val size = parts[0].toInt()
                val name = parts.drop(1).joinToString(" ")
                contents.add("$size $name")
            }
        } else {
            val parts = line.split(" ")
            when (parts[1]) {
                "cd" -> {
                    if (parts[2] == "/") {
                        currentDirectory = "/"
                    } else if (parts[2] == "..") {
                        currentDirectory = currentDirectory.substringBeforeLast("/")
                    } else {
                        currentDirectory += "/${parts[2]}"
                    }
                }

                "ls" -> {
                    contents = directories.getOrPut(currentDirectory) { mutableListOf() }
                    contents.clear()
                }
            }
        }
    }

    // calculate the total size of each directory
    val directorySizes = mutableMapOf<String, Int>()

    // find all directories with total size <= 100000
    var sum = 0
    for ((directory, _) in directories) {
        val size = calculateDirectorySize(directories, directory, directorySizes)
        if (size <= 100000) {
            sum += size
        }
    }

    println(sum)

    // find a single directory that, if deleted, would free up enough space
    val totalSize = calculateDirectorySize(directories, "/", directorySizes)
    val unusedSpace = 70000000 - totalSize
    var smallestSize = Int.MAX_VALUE
    for ((directory, _) in directories) {
        if (directory != "/") {
            val size = calculateDirectorySize(directories, directory, directorySizes)
            if (unusedSpace + size >= 30000000 && size < smallestSize) {
                smallestSize = size
            }
        }
    }

    println(smallestSize)
}