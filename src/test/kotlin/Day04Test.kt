import CampCleanup.countFullyContainedAssignments
import CampCleanup.countOverlappedAssignments
import CampCleanup.readAssignments
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day04Test {

    @Test
    fun `should count assignment pairs where one range fully contains the other`() {
        assertEquals(2, countFullyContainedAssignments(assignments))
    }

    @Test
    fun `solve day 4 part 1`() {
        assertEquals(550, countFullyContainedAssignments(puzzleInput))
    }

    @Test
    fun `should count assignment pairs where one range overlaps with the other`() {
        assertEquals(4, countOverlappedAssignments(assignments))
    }

    @Test
    fun `solve day 4 part 2`() {
        assertEquals(931, countOverlappedAssignments(puzzleInput))
    }

    companion object {
        private val assignments = listOf(
            Pair(Assignment(2, 4), Assignment(6, 8)),
            Pair(Assignment(2, 3), Assignment(4, 5)),
            Pair(Assignment(5, 7), Assignment(7, 9)),
            Pair(Assignment(2, 8), Assignment(3, 7)),
            Pair(Assignment(6, 6), Assignment(4, 6)),
            Pair(Assignment(2, 6), Assignment(4, 8)),
        )
        private val puzzleInput by lazy { readAssignments() }
    }

}