import java.io.File

object CalorieCounting {
    fun findMaxCalories(calories: List<List<Int>>): Int = calories.map { it.sum() }.max()

    fun findSumOfTopThreeMaxCalories(calories: List<List<Int>>) : Int = calories.map { it.sum() }
        .sorted().reversed().take(3).sum()

    fun readInput(): List<List<Int>> = File("src/main/resources/inputs/day01").readText()
        .trim()
        .split("\n\n").map {
            it.split("\n").map { it.toInt() }
        }
}
