import java.io.File

fun scoreAllRounds(rounds: List<Pair<String, String>>): Int = rounds
    .map { Pair(it.first.toHand(), it.second.toHand()) }
    .sumOf { Hand.scoreRound(it.second, it.first) }

fun calculateHandAndScoreAllRounds(rounds: List<Pair<String, String>>): Int = rounds
    .map { Pair(it.first.toHand(), it.second.toOutcome()) }
    .map { Pair(it.first, Hand.calculateHandToPlay(it.second, it.first)) }
    .sumOf { Hand.scoreRound(it.second, it.first) }

enum class Hand(private val score: Int) {
    ROCK(1), PAPER(2), SCISSORS(3);

    companion object {
        fun scoreRound(player: Hand, other: Hand): Int {
            return player.score + when (Pair(player, other)) {
                Pair(ROCK, ROCK) -> 3
                Pair(ROCK, PAPER) -> 0
                Pair(ROCK, SCISSORS) -> 6

                Pair(PAPER, ROCK) -> 6
                Pair(PAPER, PAPER) -> 3
                Pair(PAPER, SCISSORS) -> 0

                Pair(SCISSORS, ROCK) -> 0
                Pair(SCISSORS, PAPER) -> 6
                Pair(SCISSORS, SCISSORS) -> 3

                else -> throw IllegalStateException()
            }
        }

        fun calculateHandToPlay(outcome: Outcome, other: Hand): Hand {
            return when (Pair(outcome, other)) {
                Pair(Outcome.LOSE, ROCK) -> SCISSORS
                Pair(Outcome.LOSE, PAPER) -> ROCK
                Pair(Outcome.LOSE, SCISSORS) -> PAPER

                Pair(Outcome.DRAW, ROCK) -> ROCK
                Pair(Outcome.DRAW, PAPER) -> PAPER
                Pair(Outcome.DRAW, SCISSORS) -> SCISSORS

                Pair(Outcome.WIN, ROCK) -> PAPER
                Pair(Outcome.WIN, PAPER) -> SCISSORS
                Pair(Outcome.WIN, SCISSORS) -> ROCK

                else -> throw IllegalStateException()
            }
        }
    }
}

enum class Outcome {
    LOSE, DRAW, WIN
}

fun readInput(): List<Pair<String, String>> = File("src/main/resources/inputs/day02").readLines()
    .map { it.split(" ") }
    .filter { it.size == 2 }
    .map { Pair(it[0], it[1]) }

private fun String.toHand(): Hand {
    return when (this) {
        "A", "X" -> Hand.ROCK
        "B", "Y" -> Hand.PAPER
        "C", "Z" -> Hand.SCISSORS
        else -> throw IllegalArgumentException()
    }
}

private fun String.toOutcome(): Outcome {
    return when (this) {
        "X" -> Outcome.LOSE
        "Y" -> Outcome.DRAW
        "Z" -> Outcome.WIN
        else -> throw IllegalArgumentException()
    }
}