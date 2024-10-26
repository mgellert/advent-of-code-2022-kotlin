import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals

class Day20Test {

    @Test
    fun `should mix example input`() {
        val mix = GrovePositioningSystem.mix(testInput, LinkedList(testInput)).map { it.value }
        assertEquals(listOf<Long>(-2, 1, 2, -3, 4, 0, 3), mix)
    }

    @Test
    fun `should decrypt example`() {
        val sum = GrovePositioningSystem.sumOfCoordinates(testInput)
        assertEquals(3, sum)
    }

    @Test
    fun `should mix example with non unique numbers`() {
        val input = listOf<Long>(0, -1, -1, 1).map { GrovePositioningSystem.Container(it) }
        val mix = GrovePositioningSystem.mix(input, LinkedList(input)).map { it.value }
        assertEquals(listOf<Long>(-1, 1, -1, 0), mix)
    }

    @Test
    fun `should solve day 20 part 1`() {
        val sum = GrovePositioningSystem.sumOfCoordinates(input)
        assertEquals(4151, sum)
    }

    @Test
    fun `should decrypt using improved mixing`() {
        val mix = GrovePositioningSystem.improvedMix(testInput).map { it.value }
        assertEquals(listOf<Long>(0, -2434767459, 1623178306, 3246356612, -1623178306, 2434767459, 811589153), mix)
    }

    @Test
    fun `should solve day 20 part 2`() {
        val sum = GrovePositioningSystem.improvedSumOfCoordinates(input)
        assertEquals(7848878698663, sum)
    }

    companion object {
        private val input by lazy { GrovePositioningSystem.readGrooveCoordinates() }
        private val testInput = """
            1
            2
            -3
            3
            -2
            0
            4
        """.trimIndent().split("\n").map { GrovePositioningSystem.Container(it.trim().toLong()) }
    }
}