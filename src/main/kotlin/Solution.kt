import java.io.File

interface Solution {
    fun readInput(day: String) = File("src/main/resources/inputs/$day")
}