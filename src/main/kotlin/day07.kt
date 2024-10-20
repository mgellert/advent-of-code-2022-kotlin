object NoSpaceLeftOnDevice : Solution {

    enum class Type { DIR, FILE }
    data class Item(val name: String, val size: Long, val type: Type)

    fun sumOfDirs(commands: List<String>): Long {

        val fileSystem = mutableMapOf<String, Item>()
        val cwd = ArrayDeque<String>()

        for (command in commands) {
            if (command.startsWith("cd")) {
                val (_, dir) = command.split(" ")
                if (dir == "..") {
                    cwd.removeLast()
                } else {
                    cwd.addLast(dir)
                }
            } else {
                command.split("\n").drop(1).forEach {
                    val (first, name) = it.split(" ")
                    val path = cwd.joinToString(separator = "/") + "/" + name
                    if (first == "dir") {
                        fileSystem[cwd.joinToString(separator = "/") + "/" + name] = Item(name, 0, Type.DIR)
                    } else {
                        fileSystem[cwd.joinToString(separator = "/") + " " + name] = Item(name, first.toLong(), Type.FILE)
                    }
                }
            }
        }

        return fileSystem
            .filter { (_, item) -> item.type == Type.DIR }
            .map { (path, item) ->
                val size = fileSystem
                    .filter { (p, i) -> p.startsWith(path) && i.type == Type.FILE }
                    .map { (p, i) -> i.size }.sum()
                if (size < 100_000) size else 0
            }.sum()
    }

    fun readCommands() = readInput("day07").readText()
        .split("$ ").map { it.trim() }.filter { it.isNotBlank() }
}