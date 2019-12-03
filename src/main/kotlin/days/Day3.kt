package days

import kotlin.math.abs

class Day3 : Day(3) {

    private val path1 = inputList[0]
    private val path2 = inputList[1]

    override fun partOne(): Any {
        return closestIntersectionSum(path1, path2)
    }

    override fun partTwo(): Any {
        return fewestSteps(path1, path2)
    }

    fun closestIntersectionSum(p1: String, p2: String): Int {
        return intersections(p1, p2).minBy { it.posSum() }!!.posSum()
    }

    fun fewestSteps(p1: String, p2: String): Int {
        val (one, two) = wirePath(p1) to wirePath(p2)
        val intersections = intersections(p1, p2)
        return intersections
                .map { int -> one.indexOfFirst { it.index == int.index } + two.indexOfFirst { it.index == int.index } }
                .min() ?: 0
    }

    private fun intersections(p1: String, p2: String): List<GridCell> {
        val (one, two) = wirePath(p1) to wirePath(p2)
        return one.intersect(two).filter { !it.centralPort }
    }

    private fun wirePath(path: String): List<GridCell> {
        val instructions = path.split(',').map { it.trim() }
        return instructions.fold(listOf(GridCell(0 to 0, true))) { acc, elem -> acc.plus(acc.last().nexts(GridMove(elem))) }
    }

    data class GridCell(val index: Pair<Int, Int>, val centralPort: Boolean = false) {

        private val x = index.first
        private val y = index.second

        fun nexts(move: GridMove): List<GridCell> {
            return (move.distance downTo 1).fold(listOf(this)) { acc, _ -> acc.plus(acc.last().next(move.direction)) }.drop(1)
        }

        fun posSum(): Int = abs(x) + abs(y)

        private fun next(direction: GridDirection): GridCell {
            return when (direction) {
                GridDirection.RIGHT -> GridCell(x + 1 to y)
                GridDirection.LEFT -> GridCell(x - 1 to y)
                GridDirection.UP -> GridCell(x to y + 1)
                GridDirection.DOWN -> GridCell(x to y - 1)
            }
        }
    }

    data class GridMove(private val move: String) {
        val direction = when (move[0]) {
            'R' -> GridDirection.RIGHT
            'L' -> GridDirection.LEFT
            'D' -> GridDirection.DOWN
            'U' -> GridDirection.UP
            else -> throw IllegalArgumentException()
        }
        val distance = move.drop(1).toInt()
    }

    enum class GridDirection { LEFT, RIGHT, UP, DOWN }
}
