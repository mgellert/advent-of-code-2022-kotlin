import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day01Test {

    @Test
    fun `should find the maximum calories carried by an elf`() {
        assertEquals(CalorieCounting.findMaxCalories(calories), 24000)
    }

    @Test
    fun `should find the sum of the top 3 calories carried by an elves`() {
        assertEquals(CalorieCounting.findSumOfTopThreeMaxCalories(calories), 45000)
    }

    @Test
    fun `solve day 1 part 1`() {
        val max = CalorieCounting.findMaxCalories(input)
        assertEquals(max, 74711)
    }

    @Test
    fun `solve day 1 part 2`() {
        val sum = CalorieCounting.findSumOfTopThreeMaxCalories(input)
        assertEquals(sum, 209481)
    }

    companion object {
        private val input = CalorieCounting.readInput()
        private val calories = listOf(
            listOf(1000, 2000, 3000),
            listOf(4000),
            listOf(5000, 6000),
            listOf(7000, 8000, 9000),
            listOf(10000),
        )
    }
}