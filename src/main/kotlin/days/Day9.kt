package days

import common.IntcodeComputer
import common.stackOf

class Day9 : Day(9) {

    private val program = inputString.split(",").map { it.trim().toLong() }.toMutableList()

    override fun partOne(): Any {
        return IntcodeComputer(program).runWithIO(stackOf(1L)).last()
    }

    override fun partTwo(): Any {
        return IntcodeComputer(program).runWithIO(stackOf(2L)).last()
    }
}
