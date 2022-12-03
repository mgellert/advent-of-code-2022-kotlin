import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day02Test {

    @Test
    fun `should calculate total score if the second column represents player hand`() {
        val score = RockPaperScissors.scoreAllRounds(strategyGuide)
        assertEquals(15, score)
    }

    @Test
    fun `solve day 2 part 1`() {
        val score = RockPaperScissors.scoreAllRounds(puzzleInput)
        assertEquals(10718, score)
    }

    @Test
    fun `should calculate total score if the second column represents desired outcome`() {
        val score = RockPaperScissors.calculateHandAndScoreAllRounds(strategyGuide)
        assertEquals(12, score)
    }

    @Test
    fun `solve solve day 2 part 2`() {
        val score = RockPaperScissors.calculateHandAndScoreAllRounds(puzzleInput)
        assertEquals(14652, score)
    }

    companion object {
        private val puzzleInput by lazy { RockPaperScissors.readInput() }
        private val strategyGuide = listOf(Pair("A", "Y"), Pair("B", "X"), Pair("C", "Z"))
    }
}