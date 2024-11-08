import kotlin.test.Test
import kotlin.test.assertEquals

class Day21Test {

    @Test
    fun test1() {
        val root = MonkeyMath.calculateRoot(testInput)
        assertEquals(152, root)
    }

    @Test
    fun `should solve day21 part 1`() {
        val root = MonkeyMath.calculateRoot(input)
        assertEquals(331120084396440, root)
    }

    @Test
    fun test2() {
        val root = MonkeyMath.calculateEqualityTest(testInput)
        assertEquals(301, root)
    }

    @Test
    fun `should solve day21 part 2`() {
        val root = MonkeyMath.calculateEqualityTest(input)
        assertEquals(3378273370680, root)
    }

    companion object {
        private val input by lazy { MonkeyMath.readMonkeys() }
        private val testInput = """
            root: pppw + sjmn
            dbpl: 5
            cczh: sllz + lgvd
            zczc: 2
            ptdq: humn - dvpt
            dvpt: 3
            lfqf: 4
            humn: 5
            ljgn: 2
            sjmn: drzm * dbpl
            sllz: 4
            pppw: cczh / lfqf
            lgvd: ljgn * ptdq
            drzm: hmdt - zczc
            hmdt: 32
        """.trimIndent().split("\n").associate { line ->
            val split = line.split(": ")
            split[0] to split[1]
        }
    }
}