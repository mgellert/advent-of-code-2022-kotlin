import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day22Test {

    @Test
    fun test1() {
        val password = MonkeyMap.find2DPassword(testMap, testPath)
        assertEquals(6032, password)
    }

    @Test
    fun `should solve day 22 part 1`() {
        val (map, path) = input
        val password = MonkeyMap.find2DPassword(map, path)
        assertEquals(57350, password)
    }


    companion object {
        private val input = MonkeyMap.readMapAndPath()

        private val testInput = """
                    ...#
                    .#..
                    #...
                    ....
            ...#.......#
            ........#...
            ..#....#....
            ..........#.
                    ...#....
                    .....#..
                    .#......
                    ......#.
            
            10R5L5R10L4R5L5
        """.trimIndent().split("\n").filter { it.isNotBlank() }

        val testMap = testInput.dropLast(1)
        val testPath = Regex("\\d+|L|R").findAll(testInput.last()).map { it.value }.toList()
    }
}