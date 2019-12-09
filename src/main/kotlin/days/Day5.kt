package days

import common.IntcodeComputer
import common.stackOf

class Day5 : Day(5) {

    private fun program() = inputString.split(",").map { it.trim().toLong() }.toMutableList()

    override fun partOne(): Any {
        return IntcodeComputer(program().toMutableList()).runWithIO(stackOf(1)).last()
    }

    override fun partTwo(): Any {
        return IntcodeComputer(program().toMutableList()).runWithIO(stackOf(5)).last()
    }
}
