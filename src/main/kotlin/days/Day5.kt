package days

import common.IntcodeComputer
import java.util.Stack

class Day5 : Day(5) {

    private fun program() = inputString.split(",").map { it.trim().toInt() }.toMutableList()

    override fun partOne(): Any {
        val input = Stack<Int>()
        input.push(1)
        return IntcodeComputer(program().toMutableList()).runWithIO(input)
    }

    override fun partTwo(): Any {
        val input = Stack<Int>()
        input.push(5)
        return IntcodeComputer(program().toMutableList()).runWithIO(input)
    }
}
