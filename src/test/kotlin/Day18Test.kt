import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day18Test {

    @Test
    fun `should calculate the surface area of droplets`() {
        val surfaceArea = BoilingBoulders.surfaceArea(testInput)
        assertEquals(64, surfaceArea)
    }

    @Test
    fun `should solve day 18 part 1`() {
        val surfaceArea = BoilingBoulders.surfaceArea(input)
        assertEquals(4400, surfaceArea)
    }

    @Test
    fun `should calculate the exterior surface area of droplets`() {
        val surfaceArea = BoilingBoulders.externalSurfaceArea(testInput)
        assertEquals(58, surfaceArea)
    }

    @Test
    fun `should solve day 18 part 2`() {
        val surfaceArea = BoilingBoulders.externalSurfaceArea(input)
        assertEquals(2522, surfaceArea)
    }

    companion object {
        private val input by lazy { BoilingBoulders.readDroplets() }

        private val testInput = """
            2,2,2
            1,2,2
            3,2,2
            2,1,2
            2,3,2
            2,2,1
            2,2,3
            2,2,4
            2,2,6
            1,2,5
            3,2,5
            2,1,5
            2,3,5
        """.trimIndent()
            .split("\n")
            .map { line ->
                val (x, y, z) = line.trim().split(",").map { it.toInt() }
                Point3D(x, y, z)
            }.toSet()
    }
}