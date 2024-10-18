import java.io.File

object PyroclasticFlow : Solution {

    fun heightOfRocks(jetPattern: String, maxShapes: Long): Long {
        var shapeCounter = 0L
        var moveCounter = 0L
        var highestLine = 0L
        val restingRocks = mutableSetOf<Point>()
        val patterns = mutableMapOf<String, Pair<Long, Long>>()

        while (shapeCounter < maxShapes) {
            var rock = nextShape(shapeCounter++, Point(2, 4 + (restingRocks.maxOfOrNull { it.y } ?: highestLine)))

            while (true) {
                val move = nextMove(moveCounter++, jetPattern)
                rock = when (move) {
                    '<' -> shiftLeft(rock, restingRocks)
                    '>' -> shiftRight(rock, restingRocks)
                    else -> throw IllegalStateException()
                }
                val (hasFallen, r) = fall(rock, restingRocks, highestLine)
                rock = r
                if (!hasFallen) break
            }

            restingRocks.addAll(rock)

            val fullLines: Map<Long, List<Point>> =
                restingRocks.groupBy { it.y }.filter { (_, points) -> points.size == 7 }
            if (fullLines.isNotEmpty()) {
                highestLine = fullLines.keys.max()
                restingRocks.removeAll { it.y <= highestLine }
                val key = "${shapeCounter % 5};${moveCounter % jetPattern.length}"
                if (patterns.contains(key) && shapeCounter > 10_000) {
                    val (prevHighestLine, prevShapeCount) = patterns[key]!!
                    val deltaHighestLine = highestLine - prevHighestLine
                    val deltaShapeCount = shapeCounter - prevShapeCount
                    while (shapeCounter < maxShapes - deltaShapeCount) {
                        shapeCounter += deltaShapeCount
                        highestLine += deltaHighestLine
                    }
                    (0L..6).forEach { restingRocks.add(Point(it, highestLine)) }
                } else {
                    patterns[key] = Pair(highestLine, shapeCounter)
                }
            }
        }

        return restingRocks.maxOf { it.y }
    }

    private fun nextMove(moveCounter: Long, jetPattern: String): Char {
        val i: Int = (moveCounter % jetPattern.length).toInt()
        return jetPattern[i]
    }

    private fun nextShape(n: Long, start: Point): Set<Point> {
        when (n % 5) {
            0L -> return setOf(
                start,
                start.copy(x = start.x + 1),
                start.copy(x = start.x + 2),
                start.copy(x = start.x + 3),
            )

            1L -> return setOf(
                start.copy(x = start.x + 0, y = start.y + 1),
                start.copy(x = start.x + 1, y = start.y + 0),
                start.copy(x = start.x + 1, y = start.y + 1),
                start.copy(x = start.x + 1, y = start.y + 2),
                start.copy(x = start.x + 2, y = start.y + 1),
            )

            2L -> return setOf(
                start.copy(x = start.x + 0, y = start.y + 0),
                start.copy(x = start.x + 1, y = start.y + 0),
                start.copy(x = start.x + 2, y = start.y + 0),
                start.copy(x = start.x + 2, y = start.y + 1),
                start.copy(x = start.x + 2, y = start.y + 2),
            )

            3L -> return setOf(
                start.copy(x = start.x, y = start.y + 0),
                start.copy(x = start.x, y = start.y + 1),
                start.copy(x = start.x, y = start.y + 2),
                start.copy(x = start.x, y = start.y + 3),
            )

            4L -> return setOf(
                start.copy(x = start.x + 0, y = start.y + 0),
                start.copy(x = start.x + 1, y = start.y + 0),
                start.copy(x = start.x + 0, y = start.y + 1),
                start.copy(x = start.x + 1, y = start.y + 1),
            )

            else -> throw IllegalStateException()
        }
    }

    private fun shiftLeft(rock: Set<Point>, resting: Set<Point>): Set<Point> {
        val movedRock = rock.map { it.copy(x = it.x - 1) }.toSet()
        return if (movedRock.all { it.x >= 0 && !resting.contains(it) }) {
            movedRock
        } else {
            rock
        }
    }

    private fun shiftRight(rock: Set<Point>, resting: Set<Point>): Set<Point> {
        val movedRock = rock.map { it.copy(x = it.x + 1) }.toSet()
        return if (movedRock.all { it.x <= 6 && !resting.contains(it) }) {
            movedRock
        } else {
            rock
        }
    }

    private fun fall(rock: Set<Point>, resting: Set<Point>, baseLine: Long): Pair<Boolean, Set<Point>> {
        val movedRock = rock.map { it.copy(y = it.y - 1) }.toSet()
        return if (movedRock.all { it.y > baseLine && !resting.contains(it) }) {
            Pair(true, movedRock)
        } else {
            Pair(false, rock)
        }
    }

    fun jetPatterns() = File("src/main/resources/inputs/day17").readText().trim()
}

data class Point(val x: Long, val y: Long)
