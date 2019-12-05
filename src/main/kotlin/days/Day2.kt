package days

import common.IntcodeComputer

class Day2 : Day(2) {

    private fun program() = inputString.split(",").map { it.trim().toInt() }.toMutableList()

    override fun partOne(): Any {
        return IntcodeComputer(program().toMutableList(), 12, 2).run()
    }

    override fun partTwo(): Any {
        val (noun, verb) = (0..99).flatMap { noun -> (0..99).map { verb -> Pair(noun, verb) }}
                .find { IntcodeComputer(program().toMutableList(), it.first, it.second).run() == 19690720 } ?: Pair(0, 0)
        return 100 * noun + verb
    }
}
