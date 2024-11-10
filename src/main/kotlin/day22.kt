object MonkeyMap : Solution {

    fun findPassword(map: List<String>, path: List<String>): Long {

        val teleports = generate2DTeleports(map)

        var p = Point(map.first().indexOfFirst { it == '.' }.toLong(), 0)
        var rotation = 0

        val maxY = map.size
        val maxX = map.maxOfOrNull { it.length }!!

        for (item in path) {
            if (item == "L") {
                rotation -= 1
                if (rotation < 0) rotation += 4
            } else if (item == "R") {
                rotation += 1
                if (rotation > 3) rotation -= 4
            } else {
                val steps = item.toInt()
                val delta = getDelta(rotation)
                var counter = 0

                while (counter < steps) {
                    val temp = p + delta
                    val c = getOrDefault(map, temp)

                    if (c == '#') {
                        break
                    } else {
                        if (teleports.containsKey(temp)) {
                            val temp2  = teleports[temp]!!
                            val c2 = getOrDefault(map,  temp2)
                            if (c2 == '.') {
                                p = temp2
                            } else {
                                break
                            }


                        } else {
                            p = temp
                        }
                    }
                    counter++
                }
            }
        }

        return 1000 * (p.y + 1) + 4 * (p.x + 1) + rotation % 4
    }

    private fun getOrDefault(map: List<String>, temp: Point): Char {
        try {
            return map[temp.y.toInt()][temp.x.toInt()]
        } catch (ex: IndexOutOfBoundsException) {
            return ' '
        }
    }

    private fun generate2DTeleports(map: List<String>): Map<Point, Point> {
        val edge = map.first().count { !it.isWhitespace() }.toLong()

        val teleports = mutableMapOf<Point, Point>()

//        // top right
//        (0..<edge).forEach { y -> teleports[Point(3 * edge, y)] = Point(2 * edge, y) }
//        // middle right
//        (edge..<edge * 2).forEach { y -> teleports[Point(3 * edge, y)] = Point(0, y) }
//        // bottom right
//        (edge * 2..<edge * 3).forEach { y -> teleports[Point(4 * edge, y)] = Point(2 * edge, y) }
//
//        // bottom left and bottom middle
//        (0..<edge * 2).forEach { x -> teleports[Point(x, 2 * edge)] = Point(x, edge) }
//        // bottom middle
//        (edge * 2..<edge * 3).forEach { x -> teleports[Point(x, 3 * edge)] = Point(x, 0) }
//        // bottom right
//        (edge * 3..<edge * 4).forEach { x -> teleports[Point(x, 3 * edge)] = Point(x, 2 * edge) }


        map.forEachIndexed { i, line ->
            val idx = i.toLong()
            val first = line.indexOfFirst { it == '.' || it == '#' }.toLong()
            val last = line.indexOfLast { it == '.' || it == '#' }.toLong()

            teleports[Point(first - 1, idx)] = Point(last, idx)
            teleports[Point(last + 1, idx)] = Point(first, idx)
        }

        val cols = map.maxOfOrNull { it.length }!!
        (0 ..< cols).forEach { col ->
            val idx = col.toLong()
            val first = map.filter { it.length > col }.indexOfFirst { it[col] == '.' || it[col] == '#' }.toLong()
            val last = map.filter { it.length > col }.indexOfLast { it[col] == '.' || it[col] == '#' }.toLong()

            teleports[Point(idx, first - 1)] = Point(idx, last)
            teleports[Point(idx, last + 1)] = Point(idx, first)
        }

        return teleports
    }

    private operator fun Point.plus(other: Point): Point {
        return Point(this.x + other.x, this.y + other.y)
    }

    private fun getDelta(rotation: Int): Point {
        return when (rotation % 4) {
            0 -> Point(1, 0)
            1 -> Point(0, 1)
            2 -> Point(-1, 0)
            3 -> Point(0, -1)
            else -> throw IllegalStateException()
        }
    }

    fun readMapAndPath() : Pair<List<String>, List<String>> {
        val input = readInput("day22").readLines().filter { it.isNotBlank() }
        val map = input.dropLast(1)
        val path = Regex("\\d+|L|R").findAll(input.last()).map { it.value }.toList()
        return Pair(map, path)
    }
}