object MonkeyMap : Solution {

    fun find2DPassword(input: List<String>, path: List<String>): Long {
        val maxX = input.maxOfOrNull { it.length }!!
        val map = input.map { it.padEnd(maxX) }

        return findPassword(map, path, generate2DTeleports(map))
    }

    private fun findPassword(
        map: List<String>,
        path: List<String>,
        teleports: Map<RotationPoint, RotationPoint>
    ): Long {
        var currentPoint = RotationPoint(map.first().indexOfFirst { it == '.' }.toLong(), 0, 0)

        for (item in path) {
            if (item == "L" || item == "R") {
                currentPoint = currentPoint.rotate(item)
            } else {
                val steps = item.toInt()
                val delta = currentPoint.getDelta()
                var counter = 0

                while (counter < steps) {
                    val nextPoint = if (teleports.containsKey(currentPoint + delta)) {
                        teleports[currentPoint + delta]!!
                    } else {
                        currentPoint + delta
                    }
                    val nextChar = getOrDefault(map, nextPoint)

                    currentPoint = if (nextChar == '#') {
                        break
                    } else {
                        nextPoint
                    }
                    counter++
                }
            }
        }

        return 1000 * (currentPoint.y + 1) + 4 * (currentPoint.x + 1) + currentPoint.rotation % 4
    }

    private fun getOrDefault(map: List<String>, p: RotationPoint): Char {
        return if (0 <= p.y && p.y < map.size && p.x >= 0 && p.x < map[p.y.toInt()].length) {
            map[p.y.toInt()][p.x.toInt()]
        } else {
            ' '
        }
    }

    private fun generate2DTeleports(map: List<String>): Map<RotationPoint, RotationPoint> {
        val teleports = mutableMapOf<RotationPoint, RotationPoint>()

        map.forEachIndexed { i, line ->
            val idx = i.toLong()
            val first = line.indexOfFirst { it == '.' || it == '#' }.toLong()
            val last = line.indexOfLast { it == '.' || it == '#' }.toLong()

            teleports[RotationPoint(first - 1, idx, 2)] = RotationPoint(last, idx, 2)
            teleports[RotationPoint(last + 1, idx, 0)] = RotationPoint(first, idx, 0)
        }

        val cols = map.maxOfOrNull { it.length }!!
        (0..<cols).forEach { col ->
            val idx = col.toLong()
            val first = map.filter { it.length > col }.indexOfFirst { it[col] == '.' || it[col] == '#' }.toLong()
            val last = map.filter { it.length > col }.indexOfLast { it[col] == '.' || it[col] == '#' }.toLong()

            teleports[RotationPoint(idx, first - 1, 3)] = RotationPoint(idx, last, 3)
            teleports[RotationPoint(idx, last + 1, 1)] = RotationPoint(idx, first, 1)
        }

        return teleports
    }

    fun readMapAndPath(): Pair<List<String>, List<String>> {
        val input = readInput("day22").readLines().filter { it.isNotBlank() }
        val map = input.dropLast(1)
        val path = Regex("\\d+|L|R").findAll(input.last()).map { it.value }.toList()
        return Pair(map, path)
    }

    data class RotationPoint(val x: Long, val y: Long, val rotation: Int) {
        fun rotate(rotation: String): RotationPoint {
            val nextRotation = when (rotation) {
                "R" -> {
                    val r = this.rotation + 1
                    if (r > 3) r - 4 else r
                }

                "L" -> {
                    val r = this.rotation - 1
                    if (r < 0) r + 4 else r
                }

                else -> throw IllegalStateException()
            }
            return this.copy(rotation = nextRotation)
        }

        fun getDelta(): RotationPoint = when (this.rotation) {
            0 -> RotationPoint(1, 0, 0)
            1 -> RotationPoint(0, 1, 0)
            2 -> RotationPoint(-1, 0, 0)
            3 -> RotationPoint(0, -1, 0)
            else -> throw IllegalStateException()
        }

        operator fun plus(other: RotationPoint): RotationPoint {
            return RotationPoint(this.x + other.x, this.y + other.y, this.rotation + other.rotation)
        }
    }
}