import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@Disabled // very slow needs optimization
class Day19Test {

    @Test
    fun `should parse a line of input`() {
        val blueprint = NotEnoughMinerals.parseLine(testInput.split("\n").first())
        assertEquals(
            Blueprint(
                1, Minerals(4, 0, 0), Minerals(2, 0, 0), Minerals(3, 14, 0), Minerals(2, 0, 7)
            ), blueprint
        )
    }

    @Test
    fun `should find max geodes for blueprint 1 from test input`() {
        val blueprint = NotEnoughMinerals.parseLine(testInput.split("\n").first())

        val quality = NotEnoughMinerals.findMostGeode(
            blueprint,
            Minerals(1, 0, 0, 0),
            Minerals(0, 0, 0, 0),
            Minerals(0, 0, 0, 0),
            1
        )
        assertEquals(9, quality)
    }

    @Test
    fun `should find max geodes for blueprint 2 from test input`() {
        val blueprint = NotEnoughMinerals.parseLine(testInput.split("\n").drop(1).first())

        val quality = NotEnoughMinerals.findMostGeode(
            blueprint,
            Minerals(1, 0, 0, 0),
            Minerals(0, 0, 0, 0),
            Minerals(0, 0, 0, 0),
            1
        )
        assertEquals(24, quality)
    }

    @Test
    fun test1() {
        val blueprint = NotEnoughMinerals.parseLine(testInput.split("\n").first())

        val quality = NotEnoughMinerals.findMostGeode(
            blueprint,
            Minerals(1, 0, 0, 0),
            Minerals(0, 0, 0, 0),
            Minerals(0, 0, 0, 0),
            1, maxTime = 32
        )
        assertEquals(56, quality)
    }

    @Test
    fun test2() {
        val blueprint = NotEnoughMinerals.parseLine(testInput.split("\n").drop(1).first())

        val quality = NotEnoughMinerals.findMostGeode(
            blueprint,
            Minerals(1, 0, 0, 0),
            Minerals(0, 0, 0, 0),
            Minerals(0, 0, 0, 0),
            1, maxTime = 32
        )
        assertEquals(62, quality)
    }

    @Test
    fun `should find the sum of blueprint qualities`() {
        val blueprints = testInput.split("\n").map { NotEnoughMinerals.parseLine(it) }
        val quality = NotEnoughMinerals.findSumOfBlueprintQualities(blueprints)
        assertEquals(33, quality)
    }

    @Test
    fun `should solve day 19 part 1`() {
        val quality = NotEnoughMinerals.findSumOfBlueprintQualities(input)
        assertEquals(2301, quality)
    }

    @Test
    fun `should solve day 19 part 2`() {
        val quality = NotEnoughMinerals.findSumOfFirstThreeBlueprints(input)
        assertEquals(10336, quality)
    }

    companion object {
        private val testInput = """
            Blueprint 1: Each ore robot costs 4 ore. Each clay robot costs 2 ore. Each obsidian robot costs 3 ore and 14 clay. Each geode robot costs 2 ore and 7 obsidian.
            Blueprint 2: Each ore robot costs 2 ore. Each clay robot costs 3 ore. Each obsidian robot costs 3 ore and 8 clay. Each geode robot costs 3 ore and 12 obsidian.
        """.trimIndent()

        private val input by lazy { NotEnoughMinerals.readBlueprints() }
    }
}