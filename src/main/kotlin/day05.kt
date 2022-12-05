import java.util.*

object SupplyStacks : Solution {

    fun moveStacks(
        supplyStack: SupplyStack,
        operations: List<Operation>,
        crateMover: (SupplyStack, Operation) -> Unit
    ): String {
        operations.forEach { crateMover(supplyStack, it) }

        val topCrates = StringBuilder()
        supplyStack.filter { !it.isEmpty() }
            .map { stack -> topCrates.append(stack.peek()) }
        return topCrates.toString()
    }

    fun crateMover9000(state: SupplyStack, op: Operation) {
        repeat(op.amount) {
            val crate = state[op.from - 1].pop()
            state[op.to - 1].push(crate)
        }
    }

    fun crateMover9001(state: SupplyStack, op: Operation) {
        val crates = state[op.from - 1].popMultiple(op.amount)
        state[op.to - 1].pushMultiple(crates)
    }

    private fun Deque<Char>.popMultiple(amount: Int): List<Char> {
        val popped = mutableListOf<Char>()
        repeat(amount) {
            popped.add(this.pop())
        }
        return popped.toList()
    }

    private fun Deque<Char>.pushMultiple(l: List<Char>) = l.reversed().forEach { this.push(it) }

    fun readOperations() = readInput("day05").readLines()
        .filter { it.startsWith("move") }
        .map {
            operationRegex.matchEntire(it)
                ?.destructured
                ?.let { (amount, from, to) ->
                    Operation(amount.toInt(), from.toInt(), to.toInt())
                }
                ?: throw IllegalStateException()
        }

    fun dequeOf(vararg chars: Char): Deque<Char> = LinkedList(chars.reversed().toMutableList())

    private val operationRegex = "move (\\d+) from (\\d+) to (\\d+)".toRegex()
}

data class Operation(val amount: Int, val from: Int, val to: Int)

typealias SupplyStack = List<Deque<Char>>
