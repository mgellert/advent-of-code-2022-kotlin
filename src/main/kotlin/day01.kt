object CalorieCounting : Solution {
    fun findMaxCalories(calories: List<List<Calorie>>): Calorie = calories.map { it.sum() }.max()

    fun findSumOfTopThreeMaxCalories(calories: List<List<Calorie>>): Calorie = calories.map { it.sum() }
        .sorted().reversed().take(3).sum()

    fun readPuzzleInput(): List<List<Calorie>> = readInput("day01").readText()
        .trim()
        .split("\n\n").map { it.split("\n").map { it.toInt() } }
}

typealias Calorie = Int