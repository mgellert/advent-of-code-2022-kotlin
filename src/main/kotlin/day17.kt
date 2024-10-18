import java.io.File

object PyroclasticFlow : Solution {

    fun heightOfRocks(jetPattern: String, maxShapes: Long): Long {
        val moveItr = moveGenerator(jetPattern).iterator()
        var shapeCounter = 0L
        val resting = mutableSetOf<Point>()

        while (shapeCounter < maxShapes) {
            var rock = nextShape(shapeCounter++, Point(2, 4 + (resting.maxOfOrNull { it.y } ?: 0)))

            while (true) {
                val move = moveItr.next()
                rock = when (move) {
                    '<' -> shiftLeft(rock, resting)
                    '>' -> shiftRight(rock, resting)
                    else -> throw IllegalStateException()
                }
                val (hasFallen, r) = fall(rock, resting)
                rock = r
                if (!hasFallen) break
            }

            resting.addAll(rock)
        }

        return resting.maxOf { it.y }
    }

    private fun moveGenerator(jetPattern: String) = sequence {
        var i = 0
        while (true) {
            yield(jetPattern[i++])
            if (i >= jetPattern.length) {
                i -= jetPattern.length
            }
        }
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

    private fun fall(rock: Set<Point>, resting: Set<Point>): Pair<Boolean, Set<Point>> {
        val movedRock = rock.map { it.copy(y = it.y - 1) }.toSet()
        return if (movedRock.all { it.y > 0 && !resting.contains(it) }) {
            Pair(true, movedRock)
        } else {
            Pair(false, rock)
        }
    }

    fun jetPatterns() = File("src/main/resources/inputs/day17").readText().trim()
}

data class Point(val x: Long, val y: Long)
