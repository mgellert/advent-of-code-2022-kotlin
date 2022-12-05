import SupplyStacks.crateMover9000
import SupplyStacks.crateMover9001
import SupplyStacks.moveStacks
import SupplyStacks.readOperations
import SupplyStacks.dequeOf
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day05Test {

    @Test
    fun `should parse input`() {
        assertEquals(505, operations.size)
        assertEquals(Operation(6, 5, 7), operations[0])
        assertEquals(Operation(13, 7, 8), operations[6])
    }

    @Test
    fun `should run rearrange procedure and get the top crate from each stack`() {
        assertEquals("CMZ", moveStacks(testInitialStack(), testOperations) { stack, operation -> crateMover9000(stack, operation) })
    }

    @Test
    fun `solve day 5 part 1`() {
        assertEquals("ZSQVCCJLL", moveStacks(initialStack(), operations) { stack, operation -> crateMover9000(stack, operation) })
    }

    @Test
    fun `should run modified rearrange procedure and get the top crate from each stack`() {
        assertEquals("MCD", moveStacks(testInitialStack(), testOperations) { stack, operation -> crateMover9001(stack, operation) })
    }

    @Test
    fun `solve day 5 part 2`() {
        assertEquals("QZFJRWHGS", moveStacks(initialStack(), operations) { stack, operation -> crateMover9001(stack, operation) })
    }

    companion object {
        private fun initialStack() = listOf(
            "RGJBTVZ",
            "JRVL",
            "SQF",
            "ZHNLFVQG",
            "RQTJCSMW",
            "SWTCHF",
            "DZCVFNJ",
            "LGZDWRFQ",
            "JBWVP"
        ).map { dequeOf(*it.toCharArray()) }

        private val operations by lazy { readOperations() }

        private fun testInitialStack() = listOf(
            dequeOf('Z', 'N'),
            dequeOf('M', 'C', 'D'),
            dequeOf('P'),
        )

        private val testOperations = listOf(
            Operation(1, 2, 1),
            Operation(3, 1, 3),
            Operation(2, 2, 1),
            Operation(1, 1, 2),
        )
    }
}