package days

import common.IntcodeComputer
import common.stackOf

class Day19 : Day(19) {

    private fun program() = inputString.split(",").map { it.trim().toLong() }.toMutableList()

    override fun partOne(): Any {
        val tractorPoints = getTractorPoints(49, 49)
        return tractorPoints.count { it.pulled }
    }

    // todo this takes 18s on my machine - speed up
    override fun partTwo(): Any {
        System.err.println("Part 2 takes around 20s... result is coming")

        // will need about 100 + half again hashes to be able to fit the square as the beam leans away
        // skip 5 rows at a time for performance as this doesn't need to be too accurate
        var y = generateSequence(0L) { it + 5 }.map { getTractorPointsRow(it) }.indexOfFirst { it.count { it.pulled } > 150 } * 5L

        val closestToEmitter: Pair<Long, Long>?
        while (true) {
            val row = getTractorPointsRow(++y)
            val lastHashInRow = row.findLast { it.pulled }
            if (lastHashInRow != null) {
                val hashX = lastHashInRow.coord.first
                val rowPlus100 = getTractorPointsRow(y + 99)
                if (rowPlus100[hashX.toInt()].pulled && rowPlus100[hashX.toInt() - 99].pulled) {
                    closestToEmitter = lastHashInRow.coord.first - 99 to lastHashInRow.coord.second
                    break
                }
            }
        }
        return closestToEmitter?.let { (it.first * 10000) + it.second } ?: 0
    }

    data class TractorPoint(val coord: Pair<Long, Long>, val pulled: Boolean)

    fun getTractorPoints(highX: Long, highY: Long): List<TractorPoint> {
        return (0L..highY).flatMap { y -> (0L..highX).map { x ->
            val out = IntcodeComputer(program()).runWithIO(stackOf(x, y)).last()
            TractorPoint(x to y, out == 1L)
        } }
    }

    fun asString(points: List<TractorPoint>): String {
        val highY = points.maxBy { it.coord.second }?.coord?.second ?: 1
        return (0L..highY).joinToString("\n") { y -> points.getRow(y).map { if (it.pulled) '#' else '.' }.joinToString("") }
    }

    private fun getTractorPointsRow(y: Long): List<TractorPoint> {
        return (0L..y).map { x ->
            val out = IntcodeComputer(program()).runWithIO(stackOf(x, y)).last()
            TractorPoint(x to y, out == 1L)
        }
    }

    private fun List<TractorPoint>.getRow(y: Long): List<TractorPoint> {
        return filter { it.coord.second == y }.sortedBy { it.coord.first }
    }
}
