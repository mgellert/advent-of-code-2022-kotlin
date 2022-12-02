import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day02Test {

    private val testInput = listOf(Pair("A", "Y"), Pair("B", "X"), Pair("C", "Z"))

    @Test
    fun `should calculate total score if the second column represents player hand`() {
        val score = scoreAllRounds(testInput)
        assertEquals(15, score)
    }

    @Test
    fun `solve day 2 part 1`() {
        val score = scoreAllRounds(readInput())
        assertEquals(10718, score)
    }

    @Test
    fun `should calculate total score if the second column represents desired outcome`() {
        val score = calculateHandAndScoreAllRounds(testInput)
        assertEquals(12, score)
    }

    @Test
    fun `solve solve day 2 part 2`() {
        val score = calculateHandAndScoreAllRounds(readInput())
        assertEquals(14652, score)
    }
}