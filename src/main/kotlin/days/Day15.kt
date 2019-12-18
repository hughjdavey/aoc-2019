package days

import common.IntcodeComputer
import java.util.UUID

class Day15 : Day(15) {

    private fun program() = inputString.split(",").map { it.trim().toLong() }.toMutableList()

    override fun partOne(): Any {
        return RepairDroid().doUntil { it.position?.statusCode == 2 }?.position?.distanceTravelled ?: 0
    }

    override fun partTwo(): Any {
        visited.clear()
        val foundOxygen = RepairDroid().doUntil { it.position?.statusCode == 2 }!!
        foundOxygen.distanceTravelled = 0
        visited.clear()
        return foundOxygen.doUntil { it.possibleDirections().isEmpty() }?.position?.distanceTravelled ?: 0
    }

    data class Visited(val coord: Pair<Int, Int>, val statusCode: Int, val distanceTravelled: Int)

    enum class Direction(val direction: Long, val reverse: Long) {

        NORTH(1, 2), SOUTH(2, 1), WEST(3, 4), EAST(4, 3);

        fun update(location: Pair<Int, Int>): Pair<Int, Int> {
            return when (this) {
                NORTH -> location.first to location.second - 1
                SOUTH -> location.first to location.second + 1
                WEST -> location.first - 1 to location.second
                EAST -> location.first + 1 to location.second
            }
        }

        companion object {
            fun all() = listOf(NORTH, SOUTH, EAST, WEST)
        }
    }

    inner class RepairDroid(private val program: MutableList<Long> = program(), private var location: Pair<Int, Int> = 0 to 0, var distanceTravelled: Int = 0, var position: Visited? = null) {

        private val computer = IntcodeComputer(program)

        fun doUntil(condition: (d: RepairDroid) -> Boolean): RepairDroid? {
            while (true) {
                if (condition(this)) {
                    return this
                }

                val possibleDirections = possibleDirections()
                when {
                    possibleDirections.isEmpty() -> return null
                    possibleDirections.size == 1 -> move(possibleDirections[0])
                    else -> {
                        val droids = fork(possibleDirections, condition)
                        return droids.filterNotNull().maxBy { it.distanceTravelled }
                    }
                }
            }
        }

        fun possibleDirections(): List<Direction> {
            return Direction.all().filter {
                val status = computer.runWithIO(it.direction).last()
                if (status == 0L) false else {
                    computer.runWithIO(it.reverse)
                    true
                }
            }.filter { visited.find { vis -> it.update(location) == vis.coord } == null }
        }

        private fun move(direction: Direction) {
            val status = computer.runWithIO(direction.direction).last()
            location = direction.update(location)
            distanceTravelled += 1
            position = Visited(location, status.toInt(), distanceTravelled).also { visited.add(it) }
        }

        private fun fork(directions: List<Direction>, condition: (d: RepairDroid) -> Boolean): List<RepairDroid?> {
            return directions.map {
                val copy = RepairDroid(program.toMutableList(), location, distanceTravelled)
                copy.move(it)
                copy.doUntil(condition)
            }
        }
    }

    companion object {

        private val visited = mutableListOf<Visited>()
    }
}
