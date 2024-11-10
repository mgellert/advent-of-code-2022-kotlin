import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day22Test {

    @Test
    fun test1() {
        val password = MonkeyMap.findPassword(testMap, testPath)
        assertEquals(1038, password)
    }

    @Test
    fun `should solve day 22 part 1`() {
        val (map, path) = input
        val password = MonkeyMap.findPassword(map, path)
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
            
            RR1
        """.trimIndent().split("\n").filter { it.isNotBlank() }

        val testMap = testInput.dropLast(1)
        val testPath = Regex("\\d+|L|R").findAll(testInput.last()).map { it.value }.toList()
    }
}