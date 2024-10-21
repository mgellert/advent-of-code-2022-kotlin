object NotEnoughMinerals : Solution {
    private val regex = "\\d+".toRegex()

    fun parseLine(line: String): Blueprint {
        val numbers = regex.findAll(line).map { it.value.toInt() }.toList()
        return Blueprint(
            numbers[0],
            Minerals(numbers[1], 0, 0),
            Minerals(numbers[2], 0, 0),
            Minerals(numbers[3], numbers[4], 0),
            Minerals(numbers[5], 0, numbers[6])
        )
    }

    fun readBlueprints(): List<Blueprint> = readInput("day19").readLines().map { parseLine(it) }

    fun findSumOfBlueprintQualities(blueprints: List<Blueprint>): Int {
        return blueprints.sumOf { blueprint ->
            findMostGeode(
                blueprint,
                Minerals(1, 0, 0, 0),
                Minerals(0, 0, 0, 0),
                Minerals(0, 0, 0, 0),
                1
            )
        }
    }

    fun findMostGeode(blueprint: Blueprint, robots: Minerals, factory: Minerals, minerals: Minerals, time: Int): Int {
        val minedMinerals = minerals + robots

        if (time == 24) {
            return minedMinerals.geode * blueprint.id
        }

        val availableRobots = robots + factory

        val geodes = mutableSetOf<Int>()
        if (buildOreRobot(blueprint, minedMinerals)) {
            geodes.add(
                findMostGeode(
                    blueprint,
                    availableRobots,
                    Minerals(1, 0, 0, 0),
                    minedMinerals - blueprint.oreRobotCost,
                    time + 1
                )
            )
        }

        if (buildClayRobot(blueprint, minedMinerals)) {
            geodes.add(
                findMostGeode(
                    blueprint,
                    availableRobots,
                    Minerals(0, 1, 0, 0),
                    minedMinerals - blueprint.clayRobotCost,
                    time + 1
                )
            )
        }

        if (buildObsidianRobot(blueprint, minedMinerals)) {
            geodes.add(
                findMostGeode(
                    blueprint,
                    availableRobots,
                    Minerals(0, 0, 1, 0),
                    minedMinerals - blueprint.obsidianRobotCost,
                    time + 1
                )
            )
        }

        if (buildGeodeRobot(blueprint, minedMinerals)) {
            geodes.add(
                findMostGeode(
                    blueprint,
                    availableRobots,
                    Minerals(0, 0, 0, 1),
                    minedMinerals - blueprint.geodeRobotCost,
                    time + 1
                )
            )
        }

        // do nothing
        geodes.add(
            findMostGeode(
                blueprint,
                availableRobots,
                Minerals(0, 0, 0, 0),
                minedMinerals, time + 1
            )
        )

        return geodes.maxOrNull() ?: 0
    }

    private fun buildGeodeRobot(blueprint: Blueprint, minerals: Minerals): Boolean {
        return minerals.ore >= blueprint.geodeRobotCost.ore && minerals.obsidian >= blueprint.geodeRobotCost.obsidian
    }

    private fun buildObsidianRobot(blueprint: Blueprint, minerals: Minerals): Boolean {
        return minerals.ore >= blueprint.obsidianRobotCost.ore && minerals.clay >= blueprint.obsidianRobotCost.clay
                && minerals.obsidian <= blueprint.maxObsidianNeeded()
    }

    private fun buildClayRobot(blueprint: Blueprint, minerals: Minerals): Boolean {
        return minerals.ore >= blueprint.clayRobotCost.ore
                && minerals.clay <= blueprint.maxClayNeeded()
    }

    private fun buildOreRobot(blueprint: Blueprint, minerals: Minerals): Boolean {
        return minerals.ore >= blueprint.oreRobotCost.ore
                && minerals.ore <= blueprint.maxOreNeeded()
    }


}

data class Minerals(val ore: Int, val clay: Int, val obsidian: Int, val geode: Int = 0) {
    operator fun plus(other: Minerals): Minerals {
        return Minerals(
            this.ore + other.ore,
            this.clay + other.clay,
            this.obsidian + other.obsidian,
            this.geode + other.geode
        )
    }

    operator fun minus(other: Minerals): Minerals {
        return Minerals(
            this.ore - other.ore,
            this.clay - other.clay,
            this.obsidian - other.obsidian,
            this.geode - other.geode
        )
    }
}


data class Blueprint(
    val id: Int,
    val oreRobotCost: Minerals,
    val clayRobotCost: Minerals,
    val obsidianRobotCost: Minerals,
    val geodeRobotCost: Minerals
) {
    fun maxOreNeeded(): Int =
        setOf(
            this.oreRobotCost.ore,
            this.clayRobotCost.ore,
            this.obsidianRobotCost.ore,
            this.geodeRobotCost.ore
        ).max()

    fun maxClayNeeded(): Int = this.obsidianRobotCost.clay

    fun maxObsidianNeeded(): Int = this.geodeRobotCost.obsidian
}