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
            blueprint.id * findMostGeode(
                blueprint,
                Minerals(1, 0, 0, 0),
                Minerals(0, 0, 0, 0),
                Minerals(0, 0, 0, 0),
                1
            )
        }

    }

    fun findSumOfFirstThreeBlueprints(blueprints: List<Blueprint>): Int {
        return blueprints.take(3).map { blueprint ->
            findMostGeode(
                blueprint,
                Minerals(1, 0, 0, 0),
                Minerals(0, 0, 0, 0),
                Minerals(0, 0, 0, 0),
                1, 32
            )
        }.onEach { println(it) }
        .reduce(Int::times)
    }

    fun findMostGeode(
        blueprint: Blueprint,
        robots: Minerals,
        factory: Minerals,
        minerals: Minerals,
        time: Int,
        maxTime: Int = 24
    ): Int {
        val minedMinerals = minerals + robots

        if (time == maxTime) {
            return minedMinerals.geode
        }

        val availableRobots = robots + factory

        val geodes = mutableSetOf<Int>()

        val lastMinute = maxTime - time == 1
        val buildOreRobot = !lastMinute && buildOreRobot(blueprint, minedMinerals, availableRobots)
        val buildClayRobot = !lastMinute && buildClayRobot(blueprint, minedMinerals, availableRobots)
        val buildObsidianRobot = !lastMinute && buildObsidianRobot(blueprint, minedMinerals, availableRobots)
        val buildGeodeRobot = !lastMinute && buildGeodeRobot(blueprint, minedMinerals, availableRobots)

        if (!buildGeodeRobot && buildOreRobot) {
            geodes.add(
                findMostGeode(
                    blueprint,
                    availableRobots,
                    Minerals(1, 0, 0, 0),
                    minedMinerals - blueprint.oreRobotCost,
                    time + 1, maxTime
                )
            )
        }

        if (!buildGeodeRobot && buildClayRobot) {
            geodes.add(
                findMostGeode(
                    blueprint,
                    availableRobots,
                    Minerals(0, 1, 0, 0),
                    minedMinerals - blueprint.clayRobotCost,
                    time + 1, maxTime
                )
            )
        }

        if (!buildGeodeRobot && buildObsidianRobot) {
            geodes.add(
                findMostGeode(
                    blueprint,
                    availableRobots,
                    Minerals(0, 0, 1, 0),
                    minedMinerals - blueprint.obsidianRobotCost,
                    time + 1, maxTime
                )
            )
        }

        if (buildGeodeRobot) {
            geodes.add(
                findMostGeode(
                    blueprint,
                    availableRobots,
                    Minerals(0, 0, 0, 1),
                    minedMinerals - blueprint.geodeRobotCost,
                    time + 1, maxTime
                )
            )
        }

        geodes.add(
            findMostGeode(
                blueprint,
                availableRobots,
                Minerals(0, 0, 0, 0),
                minedMinerals, time + 1, maxTime
            )
        )

        return geodes.maxOrNull() ?: 0
    }

    private fun buildGeodeRobot(blueprint: Blueprint, minerals: Minerals, robots: Minerals): Boolean {
        return minerals.ore >= blueprint.geodeRobotCost.ore && minerals.obsidian >= blueprint.geodeRobotCost.obsidian
    }

    private fun buildObsidianRobot(blueprint: Blueprint, minerals: Minerals, robots: Minerals): Boolean {
        return minerals.ore >= blueprint.obsidianRobotCost.ore && minerals.clay >= blueprint.obsidianRobotCost.clay
                && minerals.obsidian <= blueprint.maxObsidianNeeded() * 1.4
                && robots.obsidian <= blueprint.maxObsidianNeeded()
    }

    private fun buildClayRobot(blueprint: Blueprint, minerals: Minerals, robots: Minerals): Boolean {
        return minerals.ore >= blueprint.clayRobotCost.ore
                && minerals.clay <= blueprint.maxClayNeeded() * 1.4
                && robots.clay <= blueprint.maxClayNeeded()
    }

    private fun buildOreRobot(blueprint: Blueprint, minerals: Minerals, robots: Minerals): Boolean {
        return minerals.ore >= blueprint.oreRobotCost.ore
                && minerals.ore <= blueprint.maxOreNeeded() * 1.4
                && robots.ore <= blueprint.maxOreNeeded()

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