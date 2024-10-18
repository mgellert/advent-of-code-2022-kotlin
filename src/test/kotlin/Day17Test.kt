import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day17Test {

    @Test
    fun `should calculate the height of rocks`() {
        val height = PyroclasticFlow.heightOfRocks(TEST_INPUT, 2022)
        assertEquals(3068, height)
    }

    @Test
    fun `should solve day 17 part 1`() {
        val height = PyroclasticFlow.heightOfRocks(input, 2022)
        assertEquals(3081, height)
    }

    @Test
    fun `should solve day 17 part 2`() {
        val height = PyroclasticFlow.heightOfRocks(input, 1000000000000)
        assertEquals(1524637681145, height)
    }

    companion object {
        private val input by lazy { PyroclasticFlow.jetPatterns() }
        const val TEST_INPUT = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
    }

}