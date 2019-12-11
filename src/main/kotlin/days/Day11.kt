package days

import common.IntcodeComputer
import common.stackOf

class Day11 : Day(11) {

    private val program = inputString.split(",").map { it.trim().toLong() }.toMutableList()

    override fun partOne(): Any {
        return Robot().paintsAtLeastOnce()
    }

    override fun partTwo(): Any {
        // HBGLZKLF
        return "\n" + Robot(Panel(0 to 0, '#')).drawPainting()
    }

    data class Panel(val coords: Pair<Int, Int>, var colour: Char) {
        val x = coords.first
        val y = coords.second
    }

    inner class Robot(private val startPanel: Panel = Panel(0 to 0, '.')) {

        private val computer = IntcodeComputer(program)
        private var facingDegrees = 0

        val visited = mutableListOf(startPanel)

        fun paint() {
            val initialInput = if (startPanel.colour == '.') 0L else 1L
            var out = computer.runWithIO(stackOf(initialInput)).takeLast(2)

            while (!computer.halted) {
                // get colour and direction from output; set current panel colour
                val (colour, direction) = out
                val currentPanel = visited.last()
                currentPanel.colour = if (colour == 0L) '.' else '#'

                // update robot direction
                if (direction == 0L) facingDegrees -= 90 else facingDegrees += 90
                if (facingDegrees < 0) facingDegrees += 360 else if (facingDegrees >= 360) facingDegrees -= 360

                // get coords of next tile robot moves to
                val newCoords = when (facingDegrees) {
                    0 -> currentPanel.x to currentPanel.y + 1
                    90 -> currentPanel.x + 1 to currentPanel.y
                    180 -> currentPanel.x to currentPanel.y - 1
                    270 -> currentPanel.x - 1 to currentPanel.y
                    else -> throw IllegalStateException("robot was facing $facingDegrees degrees!")
                }

                // if we have not been before add a new panel to visited, otherwise remove and re-add panel so it is at List#last for next loop
                val maybePanel = visited.find { it.coords == newCoords }
                if (maybePanel == null) {
                    visited.add(Panel(newCoords, '.'))
                }
                else {
                    visited.remove(maybePanel)
                    visited.add(maybePanel)
                }

                // run computer for next loop
                val newInput = if (visited.last().colour == '.') 0L else 1L
                out = computer.restartWithIO(newInput).takeLast(2)
            }
        }

        fun paintsAtLeastOnce(): Int {
            paint()
            return visited.size
        }

        fun drawPainting(): String {
            paint()
            val xExtent = visited.map { it.x }.max()?.minus(visited.map { it.x }.min()!!)!!
            val yExtent = visited.map { it.y }.max()?.minus(visited.map { it.y }.min()!!)!!
            val minX = visited.minBy { it.x }?.x ?: 0
            val minY = visited.minBy { it.y }?.y ?: 0

            val grid = Array(yExtent + 1) { Array(xExtent + 1) { "." } }.mapIndexed { y, array ->
                array.mapIndexed { x, string ->
                    val index = x to y
                    val transformed = index.first + minX to index.second + minY
                    val maybeVisited = visited.find { it.coords == transformed }
                    maybeVisited?.colour?.toString() ?: string
                }
            }
            return grid.reversed().joinToString("\n") { row -> row.joinToString(" ") }
        }
    }
}
