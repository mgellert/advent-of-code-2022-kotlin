object RucksackReorganization : Solution {
    fun findSumOfMatching(rucksacks: List<Rucksack>, priorities: Map<Char, Int> = priorities()): Int = rucksacks.map {
        val first = it.substring(0, it.length / 2).toSet()
        val second = it.substring(it.length / 2).toSet()
        first.intersect(second)
    }
        .flatten()
        .sumOf { priorities[it] ?: 0 }

    fun findSumOfBadges(rucksacks: List<Rucksack>, priorities: Map<Char, Int> = priorities()): Int =
        rucksacks.chunked(3).map {
            val (a, b, c) = it.map { rucksack -> rucksack.toSet() }
            val asd = a.intersect(b).intersect(c)
            asd
        }
            .flatten()
            .sumOf { priorities[it] ?: 0 }

    private fun priorities(): Map<Char, Int> = mutableMapOf<Char, Int>().also {
        ('a'..'z').mapIndexed { index, char -> it[char] = index + 1 }
        ('A'..'Z').mapIndexed { index, char -> it[char] = index + 27 }
    }.toMap()

    fun readRucksackContents() = readInput("day03").readLines()
}

typealias Rucksack = String