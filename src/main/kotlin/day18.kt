import java.util.LinkedList
import java.util.Queue

object BoilingBoulders : Solution {

    private val deltas = listOf(
        Point3D(-1, 0, 0),
        Point3D(1, 0, 0),
        Point3D(0, -1, 0),
        Point3D(0, 1, 0),
        Point3D(0, 0, -1),
        Point3D(0, 0, 1),
    )

    fun surfaceArea(droplets: Set<Point3D>): Int {
        return droplets.sumOf { droplet ->
            deltas
                .map { d -> Point3D(droplet.x + d.x, droplet.y + d.y, droplet.z + d.z) }
                .count { !droplets.contains(it) }
        }
    }

    fun externalSurfaceArea(droplets: Set<Point3D>): Int {
        // random point
        val startPoint = Point3D(1, 1, 1)

        val minX = droplets.minOfOrNull { it.x }!! - 1
        val maxX = droplets.maxOfOrNull { it.x }!! + 1

        val minY = droplets.minOfOrNull { it.y }!! - 1
        val maxY = droplets.maxOfOrNull { it.y }!! + 1

        val minZ = droplets.minOfOrNull { it.z }!! - 1
        val maxZ = droplets.maxOfOrNull { it.z }!! + 1

        val water = mutableSetOf<Point3D>()
        val queue: Queue<Point3D> = LinkedList()
        queue.add(startPoint)

        while (queue.isNotEmpty()) {
            val droplet = queue.poll()
            deltas
                .map { d -> Point3D(droplet.x + d.x, droplet.y + d.y, droplet.z + d.z) }
                .filter { it.x in minX..maxX && it.y in minY..maxY && it.z in minZ..maxZ }
                .filter { !droplets.contains(it) && !water.contains(it) }
                .forEach {
                    queue.add(it)
                    water.add(it)
                }
        }

        return droplets.sumOf { droplet ->
            deltas
                .map { d -> Point3D(droplet.x + d.x, droplet.y + d.y, droplet.z + d.z) }
                .count { !droplets.contains(it) && water.contains(it) }
        }
    }

    fun readDroplets() = readInput("day18")
        .readLines()
        .map { line ->
            val (x, y, z) = line.trim().split(",").map { it.toInt() }
            Point3D(x, y, z)
        }
        .toSet()
}

data class Point3D(val x: Int, val y: Int, val z: Int)