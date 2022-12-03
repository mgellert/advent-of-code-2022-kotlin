import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day03Test {

    @Test
    fun `should calculate the sum of the priorities of matching item types`() {
        val sum = RucksackReorganization.findSumOfMatching(rucksackContents)
        assertEquals(157, sum)
    }

    @Test
    fun `solve day 3 part 1`() {
        val sum = RucksackReorganization.findSumOfMatching(puzzleInput)
        assertEquals(7889, sum)
    }

    @Test
    fun `should calculate the sum of the priorities of badges`() {
        val sum = RucksackReorganization.findSumOfBadges(rucksackContents)
        assertEquals(70, sum)
    }

    @Test
    fun `solve day 3 part 2`() {
        val sum = RucksackReorganization.findSumOfBadges(puzzleInput)
        assertEquals(2825, sum)
    }

    companion object {
        private val puzzleInput by lazy { RucksackReorganization.readRucksackContents() }
        private val rucksackContents = listOf(
            "vJrwpWtwJgWrhcsFMMfFFhFp",
            "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
            "PmmdzqPrVvPwwTWBwg",
            "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
            "ttgJtRGJQctTZtZT",
            "CrZsJsPPZsGzwwsLwLmpwMDw"
        )
    }
}