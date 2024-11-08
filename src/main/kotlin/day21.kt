object MonkeyMath : Solution {

    private val numRegex = "\\d+".toRegex()

    fun calculateRoot(monkeys: Map<String, String>, monkey: String = "root"): Long {
        val value = monkeys[monkey]!!
        if (value.matches(numRegex)) {
            return value.toLong()
        }

        val (monkey1, operator, monkey2) = value.split(" ")
        return when (operator) {
            "+" -> calculateRoot(monkeys, monkey1) + calculateRoot(monkeys, monkey2)
            "-" -> calculateRoot(monkeys, monkey1) - calculateRoot(monkeys, monkey2)
            "*" -> calculateRoot(monkeys, monkey1) * calculateRoot(monkeys, monkey2)
            "/" -> calculateRoot(monkeys, monkey1) / calculateRoot(monkeys, monkey2)
            else -> throw IllegalStateException()
        }
    }

    fun calculateNumberToYell(monkeys: Map<String, String>): Long {
        val value = monkeys["root"]!!
        val (monkey1, _, monkey2) = value.split(" ")

        val (humanBranch, monkeyBranch) = if (hasHuman(monkeys, monkey1)) Pair(monkey1, monkey2) else Pair(monkey2, monkey1)
        val rootNumber = calculateRoot(monkeys, monkeyBranch)
        val (_, numberToYell) = calculateHumanNumber(monkeys, humanBranch, rootNumber)
        return numberToYell
    }

    private fun calculateHumanNumber(monkeys: Map<String, String>, monkey: String, neededNumber: Long): Pair<Long, Long> {
        if (monkey == "humn") {
            return Pair(-1, neededNumber)
        }

        val value = monkeys[monkey]!!
        if (value.matches(numRegex)) {
            return Pair(value.toLong(), -1)
        }

        val (monkey1, operator, monkey2) = value.split(" ")
        val branch1HasHuman = hasHuman(monkeys, monkey1)
        val (humanBranch, monkeyBranch) = if (branch1HasHuman) Pair(monkey1, monkey2) else Pair(monkey2, monkey1)
        val rootNumber = calculateRoot(monkeys, monkeyBranch)

        return when (operator) {
            "+" -> calculateHumanNumber(monkeys, humanBranch, neededNumber - rootNumber)
            "-" -> calculateHumanNumber(monkeys, humanBranch, if (branch1HasHuman) neededNumber + rootNumber else rootNumber - neededNumber )
            "*" -> calculateHumanNumber(monkeys, humanBranch, neededNumber / rootNumber )
            "/" -> calculateHumanNumber(monkeys, humanBranch, if (branch1HasHuman) neededNumber * rootNumber else rootNumber / neededNumber )
            else -> throw IllegalStateException()
        }
    }

    private fun hasHuman(monkeys: Map<String, String>, monkey: String): Boolean {
        if (monkey == "humn") {
            return true
        }

        val value = monkeys[monkey]!!
        if (value.matches(numRegex)) {
            return false
        }

        val (monkey1, _, monkey2) = value.split(" ")
        return hasHuman(monkeys, monkey1) || hasHuman(monkeys, monkey2)
    }

    fun readMonkeys() = readInput("day21").readLines().associate { line ->
        val split = line.split(": ")
        split[0] to split[1]
    }
}