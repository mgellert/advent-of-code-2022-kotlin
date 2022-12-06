object TuningTrouble : Solution {

    fun startOfPacket(stream: String, packetSize: Int = 4): Int = stream.windowed(packetSize)
        .indexOfFirst { it.toSet().size == it.length } + packetSize

    fun readBuffer() = readInput("day06").readText().trim()
}