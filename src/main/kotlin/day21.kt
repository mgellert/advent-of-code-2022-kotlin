object MonkeyMath : Solution {

    private val numRegex = "\\d+".toRegex()

    fun calculateRoot(monkeys: Map<String, String>, monkey: String = "root"): Long {
        val value = monkeys[monkey]!!
        if (value.matches(numRegex)) {
            return value.toLong()
        }

        val (monkey1, operator, monkey2) = value.split(" ")
        return when(operator) {
            "+" -> calculateRoot(monkeys, monkey1) + calculateRoot(monkeys, monkey2)
            "-" -> calculateRoot(monkeys, monkey1) - calculateRoot(monkeys, monkey2)
            "*" -> calculateRoot(monkeys, monkey1) * calculateRoot(monkeys, monkey2)
            "/" -> calculateRoot(monkeys, monkey1) / calculateRoot(monkeys, monkey2)
            else -> throw IllegalStateException()
        }
    }

    fun readMonkeys() = readInput("day21").readLines().associate { line ->
        val split = line.split(": ")
        split[0] to split[1]
    }
}