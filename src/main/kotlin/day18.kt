object BoilingBoulders : Solution {

    val deltas = listOf(
        Point3D(-1, 0, 0),
        Point3D(1, 0, 0),
        Point3D(0, -1, 0),
        Point3D(0, 1, 0),
        Point3D(0, 0, -1),
        Point3D(0, 0, 1),
    )

    fun surfaceArea(droplets: Set<Point3D>): Int {
        return droplets.map { droplet ->
            deltas
                .map { d -> Point3D(droplet.x + d.x, droplet.y + d.y, droplet.z + d.z) }
                .count { !droplets.contains(it) }
        }.sum()
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