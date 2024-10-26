import java.util.LinkedList

object GrovePositioningSystem : Solution {

    private const val DECRYPTION_KEY = 811589153L

    fun sumOfCoordinates(numbers: List<Container>) : Long {
        val mix = mix(numbers)

        val indexOfZero = mix.indexOfFirst { it.value == 0L }
        val first = mix[(indexOfZero + 1000) % mix.size]
        val second = mix[(indexOfZero + 2000) % mix.size]
        val third = mix[(indexOfZero + 3000) % mix.size]

        return first.value + second.value + third.value
    }

    fun improvedSumOfCoordinates(numbers: List<Container>) : Long {
        val mix = improvedMix(numbers)

        val indexOfZero = mix.indexOfFirst { it.value == 0L }
        val first = mix[(indexOfZero + 1000) % mix.size]
        val second = mix[(indexOfZero + 2000) % mix.size]
        val third = mix[(indexOfZero + 3000) % mix.size]

        return first.value + second.value + third.value
    }

    fun mix(originalNumbers: List<Container>, mix: LinkedList<Container> = LinkedList(originalNumbers)): LinkedList<Container> {

        originalNumbers.forEach { n ->
            val index = mix.indexOf(n)
            mix.removeAt(index)

            if (n.value < 0) {
                var i = (index + n.value) % mix.size
                if (i < 0) i += mix.size
                mix.add(i.toInt(), n)
            } else {
                val i = (index + n.value) % mix.size
                mix.add(i.toInt(), n)
            }
        }

        return mix
    }

    fun improvedMix(numbers: List<Container>): List<Container> {
        val multipliedNumbers: List<Container> = numbers.map { Container(it.value * DECRYPTION_KEY) }
        var mix = LinkedList(multipliedNumbers)
        repeat(10) {
            mix = mix(multipliedNumbers, mix)
        }
        return mix
    }

    fun readGrooveCoordinates() = readInput("day20")
        .readLines().map { Container(it.trim().toLong()) }

    class Container(val value: Long)
}