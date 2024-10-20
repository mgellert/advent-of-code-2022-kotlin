import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day07Test {

    @Test
    fun `should calculate the sum of directories with maximum 100 000 size`() {
        val size = NoSpaceLeftOnDevice.sumOfDirs(TEST_INPUT)
        assertEquals(95437, size)
    }

    @Test
    fun `should solve day 7 part 1`() {
        val size = NoSpaceLeftOnDevice.sumOfDirs(input)
        assertEquals(1749646, size)
    }

    @Test
    fun `should calculate the smallest dir that can be deleted`() {
        val size = NoSpaceLeftOnDevice.deleteLargestDir(TEST_INPUT)
        assertEquals(24933642, size)
    }

    @Test
    fun `should solve day 7 part 2`() {
        val size = NoSpaceLeftOnDevice.deleteLargestDir(input)
        assertEquals(1498966, size)
    }

    companion object {
        private val input by lazy { NoSpaceLeftOnDevice.readCommands() }
        private val TEST_INPUT = """
            ${'$'} cd /
            ${'$'} ls
            dir a
            14848514 b.txt
            8504156 c.dat
            dir d
            ${'$'} cd a
            ${'$'} ls
            dir e
            29116 f
            2557 g
            62596 h.lst
            ${'$'} cd e
            ${'$'} ls
            584 i
            ${'$'} cd ..
            ${'$'} cd ..
            ${'$'} cd d
            ${'$'} ls
            4060174 j
            8033020 d.log
            5626152 d.ext
            7214296 k
        """.trimIndent().split("$ ").map { it.trim() }.filter { it.isNotBlank() }
    }
}