import TuningTrouble.readBuffer
import TuningTrouble.startOfPacket
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

internal class Day06Test {

    @TestFactory
    fun testStartOfPacket() = listOf(
        "mjqjpqmgbljsphdztnvjfqwrcgsmlb" to 7,
        "bvwbjplbgvbhsrlpgdmjqwftvncz" to 5,
        "nppdvjthqldpwncqszvftbrmjlhg" to 6,
        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg" to 10,
        "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw" to 11,
    ).map { (stream, expected) ->
        DynamicTest.dynamicTest("when '${stream.substring(0..8)}...' is received find the starting packet") {
            assertEquals(expected, startOfPacket(stream))
        }
    }

    @Test
    fun `solve day 6 part 1`() {
        assertEquals(1538, startOfPacket(puzzleInput))
    }

    @TestFactory
    fun testStartOfMessage() = listOf(
        "mjqjpqmgbljsphdztnvjfqwrcgsmlb" to 19,
        "bvwbjplbgvbhsrlpgdmjqwftvncz" to 23,
        "nppdvjthqldpwncqszvftbrmjlhg" to 23,
        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg" to 29,
        "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw" to 26,
    ).map { (stream, expected) ->
        DynamicTest.dynamicTest("when '${stream.substring(0..8)}...' is received find the starting message") {
            assertEquals(expected, startOfPacket(stream,  packetSize = 14))
        }
    }

    @Test
    fun `solve day 6 part 2`() {
        assertEquals(2315, startOfPacket(puzzleInput, packetSize = 14))
    }

    companion object {
        private val puzzleInput by lazy { readBuffer() }
    }

}