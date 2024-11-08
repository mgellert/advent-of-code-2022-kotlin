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

    fun calculateEqualityTest(monkeys: Map<String, String>): Long {
        val value = monkeys["root"]!!
        val (monkey1, _, monkey2) = value.split(" ")

        val branch1 = hasHuman(monkeys, monkey1)

        val num = if (branch1) {
            calculateRoot(monkeys, monkey2)
        } else {
            calculateRoot(monkeys, monkey1)
        }

        return if (branch1) {
            equality(monkeys, monkey1, num)
        } else {
            equality(monkeys, monkey2, num)
        }
    }

    private fun equality(monkeys: Map<String, String>, monkey: String, neededNumber: Long): Long {
        if (monkey == "humn") {
            println(neededNumber)
        }
        val value = monkeys[monkey]!!
        if (value.matches(numRegex)) {
            return value.toLong()
        }

        val (monkey1, operator, monkey2) = value.split(" ")
        val branch1 = hasHuman(monkeys, monkey1)
        val num = if (branch1) {
            calculateRoot(monkeys, monkey2)
        } else {
            calculateRoot(monkeys, monkey1)
        }

        val m = if (branch1) {
            monkey1
        } else {
            monkey2
        }

        return when (operator) {
            "+" -> equality(monkeys, m, neededNumber - num)
            "-" -> equality(monkeys, m, if (branch1) neededNumber + num else num - neededNumber )
            "*" -> equality(monkeys, m, neededNumber / num )
            "/" -> equality(monkeys, m, if (branch1) neededNumber * num else num / neededNumber )
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

        val (monkey1, operator, monkey2) = value.split(" ")
        return hasHuman(monkeys, monkey1) || hasHuman(monkeys, monkey2)
    }

    fun readMonkeys() = readInput("day21").readLines().associate { line ->
        val split = line.split(": ")
        split[0] to split[1]
    }
}