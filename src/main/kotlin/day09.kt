import kotlin.math.abs
import kotlin.math.sign

object RopeBridge : Solution {

    fun moveRope(motions: List<Direction>, parts: Int): Int {
        val rope = List(parts) { Rope() }
        val tailPositions = mutableSetOf(rope.last().toPair())

        motions.forEach { direction ->
            rope[0].move(direction)

            rope.windowed(2).forEach {
                val head = it[0]
                val tail = it[1]

                tail.moveWith(head)
            }

            tailPositions.add(rope.last().toPair())
        }

        return tailPositions.size
    }

    fun readInput() = readInput("day09").readLines()
}

class Rope(private var x: Int = 0, private var y: Int = 0) {

    private fun add(x: Int, y: Int) {
        this.x += x
        this.y += y
    }

    fun move(direction: Direction) {
        when (direction) {
            Direction.UP -> this.add(0, 1)
            Direction.DOWN -> this.add(0, -1)
            Direction.LEFT -> this.add(-1, 0)
            Direction.RIGHT -> this.add(1, 0)
        }
    }

    fun moveWith(other: Rope) {
        val deltaX = abs(other.x - this.x)
        val deltaY = abs(other.y - this.y)

        if (deltaX >= 2 || deltaY >= 2) {
            this.add((other.x - this.x).sign, (other.y - this.y).sign)
        }
    }

    fun toPair(): Pair<Int, Int> = Pair(x, y)
}

enum class Direction {
    UP, DOWN, LEFT, RIGHT
}

internal fun List<String>.getMotions(): List<Direction> = this.map {
    val (motionStr, amount) = it.trim().split(" ")
    val motion = when (motionStr) {
        "U" -> Direction.UP
        "D" -> Direction.DOWN
        "L" -> Direction.LEFT
        "R" -> Direction.RIGHT
        else -> throw IllegalStateException()
    }
    List(amount.toInt()) { motion }
}.flatten()