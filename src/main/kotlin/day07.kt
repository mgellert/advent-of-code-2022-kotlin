object NoSpaceLeftOnDevice : Solution {

    enum class Type { DIR, FILE }
    data class Item(val name: String, val size: Long, val type: Type)

    fun sumOfDirs(commands: List<String>): Long {
        val fileSystem = createFileSystem(commands)

        return fileSystem
            .filter { (_, item) -> item.type == Type.DIR }
            .map { (path, _) ->
                val size = fileSystem
                    .filter { (p, i) -> p.startsWith(path) && i.type == Type.FILE }
                    .map { (_, i) -> i.size }.sum()
                if (size < 100_000) size else 0
            }.sum()
    }

    fun deleteLargestDir(commands: List<String>) : Long {
        val fileSystem = createFileSystem(commands)

        val availableSpace = 70_000_000
        val usedSpace = fileSystem.map { (_, item) -> item.size }.sum()
        val requiredSpace = 30_000_000
        val freeSpace = availableSpace - usedSpace

        return fileSystem
            .filter { (_, item) -> item.type == Type.DIR }
            .map { (path, _) ->
                 fileSystem
                    .filter { (p, i) -> p.startsWith(path) && i.type == Type.FILE }
                    .map { (_, i) -> i.size }.sum()
            }
            .filter { it > requiredSpace - freeSpace  }.min()
    }

    private fun createFileSystem(commands: List<String>): MutableMap<String, Item> {
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
                    if (first == "dir") {
                        fileSystem[cwd.joinToString(separator = "/") + "/" + name] = Item(name, 0, Type.DIR)
                    } else {
                        fileSystem[cwd.joinToString(separator = "/") + " " + name] =
                            Item(name, first.toLong(), Type.FILE)
                    }
                }
            }
        }
        return fileSystem
    }

    fun readCommands() = readInput("day07").readText()
        .split("$ ").map { it.trim() }.filter { it.isNotBlank() }
}