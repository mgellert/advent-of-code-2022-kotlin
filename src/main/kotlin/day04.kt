object CampCleanup : Solution {

    fun countFullyContainedAssignments(assignments: List<Pair<Assignment, Assignment>>): Int = count(assignments) {
        it.first.contains(it.second) || it.second.contains(it.first)
    }

    fun countOverlappedAssignments(assignments: List<Pair<Assignment, Assignment>>): Int = count(assignments) {
        it.first.overlaps(it.second) || it.second.overlaps(it.first)
    }

    fun readAssignments(): List<Pair<Assignment, Assignment>> = readInput("day04").readLines()
        .map { it.split("-", ",") }
        .flatten()
        .map { it.toInt() }
        .chunked(4)
        .map { Pair(Assignment(it[0], it[1]), Assignment(it[2], it[3])) }

    private fun count(
        assignments: List<Pair<Assignment, Assignment>>,
        function: (Pair<Assignment, Assignment>) -> Boolean
    ) = assignments.map { function(it) }.count { it }

    private fun Assignment.contains(other: Assignment): Boolean =
        this.first <= other.first && this.second >= other.second

    private fun Assignment.overlaps(other: Assignment): Boolean =
        (other.first <= this.first && this.first <= other.second) || (other.first <= this.second && this.second <= other.second)

}

typealias Assignment = Pair<Int, Int>
