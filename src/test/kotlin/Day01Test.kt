import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day01Test {

    @Test
    fun `should find the maximum calories carried by an elf`() {
        assertEquals(24000, CalorieCounting.findMaxCalories(calories))
    }

    @Test
    fun `should find the sum of the top 3 calories carried by an elves`() {
        assertEquals(45000, CalorieCounting.findSumOfTopThreeMaxCalories(calories))
    }

    @Test
    fun `solve day 1 part 1`() {
        val max = CalorieCounting.findMaxCalories(puzzleInput)
        assertEquals(74711, max)
    }

    @Test
    fun `solve day 1 part 2`() {
        val sum = CalorieCounting.findSumOfTopThreeMaxCalories(puzzleInput)
        assertEquals(209481, sum)
    }

    companion object {
        private val puzzleInput by lazy { CalorieCounting.readPuzzleInput() }

        private val calories = listOf(
            listOf(1000, 2000, 3000),
            listOf(4000),
            listOf(5000, 6000),
            listOf(7000, 8000, 9000),
            listOf(10000),
        )
    }
}