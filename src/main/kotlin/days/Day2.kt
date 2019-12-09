package days

import common.IntcodeComputer

class Day2 : Day(2) {

    private fun program() = inputString.split(",").map { it.trim().toLong() }.toMutableList()

    override fun partOne(): Any {
        return IntcodeComputer(program().toMutableList(), 12, 2).run()
    }

    override fun partTwo(): Any {
        val (noun, verb) = (0L..99).flatMap { noun -> (0L..99).map { verb -> Pair(noun, verb) }}
                .find { IntcodeComputer(program().toMutableList(), it.first, it.second).run() == 19690720L } ?: Pair(0L, 0L)
        return 100 * noun + verb
    }
}
