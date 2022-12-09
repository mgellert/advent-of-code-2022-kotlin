import Direction.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day09Test {

    @Test
    fun `should return the parsed input`() {
        val motions = testPuzzleInputFewerMotion
        assertEquals(24, motions.size)
        assertEquals(listOf(RIGHT, RIGHT, RIGHT, RIGHT, UP, UP, UP, UP, LEFT, LEFT), motions.subList(0, 10))
    }

    @Test
    fun `should move small rope`() {
        assertEquals(13, RopeBridge.moveRope(testPuzzleInputFewerMotion, parts = 2))
    }

    @Test
    fun `should solve day 9 part 1`() {
        assertEquals(6332, RopeBridge.moveRope(motions, parts = 2))
    }

    @Test
    fun `should move large rope with fewer motion`() {
        assertEquals(1, RopeBridge.moveRope(testPuzzleInputFewerMotion, parts = 10))
    }

    @Test
    fun `should move large rope with lots of motion`() {
        assertEquals(36, RopeBridge.moveRope(testPuzzleInputLotsOfMotion, parts = 10))
    }

    @Test
    fun `should solve day 9 part 2`() {
        assertEquals(2511, RopeBridge.moveRope(motions, parts = 10))
    }

    companion object {
        val motions by lazy { RopeBridge.readInput().getMotions() }

        val testPuzzleInputFewerMotion = """
        R 4
        U 4
        L 3
        D 1
        R 4
        D 1
        L 5
        R 2
        """.trimIndent().split("\n").getMotions()

        val testPuzzleInputLotsOfMotion = """
        R 5
        U 8
        L 8
        D 3
        R 17
        D 10
        L 25
        U 20
        """.trimIndent().split("\n").getMotions()
    }

}