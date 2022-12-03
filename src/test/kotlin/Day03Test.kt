import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day03Test {

    private val testInput: List<String> = listOf(
        "vJrwpWtwJgWrhcsFMMfFFhFp",
        "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
        "PmmdzqPrVvPwwTWBwg",
        "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
        "ttgJtRGJQctTZtZT",
        "CrZsJsPPZsGzwwsLwLmpwMDw"
    )

    @Test
    fun `should calculate the sum of the priorities of matching item types`() {
        val sum = findSumOfMatching(testInput)
        assertEquals(157, sum)
    }

    @Test
    fun `solve day 1 part 1`() {
        val sum = findSumOfMatching(readInputDay03())
        assertEquals(7889, sum)
    }

    @Test
    fun `should calculate the sum of the priorities of badges`() {
        val sum = findSumOfBadges(testInput)
        assertEquals(70, sum)
    }

    @Test
    fun `solve day 1 part 2`() {
        val sum = findSumOfBadges(readInputDay03())
        assertEquals(2825, sum)
    }

}