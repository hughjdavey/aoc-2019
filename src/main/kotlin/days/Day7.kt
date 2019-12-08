package days

import common.CircularList
import common.IntcodeComputer
import common.permutations
import common.stackOf

class Day7 : Day(7) {

    private val program = inputString.split(",").map { it.trim().toInt() }.toMutableList()

    override fun partOne(): Any {
        return permutations(listOf(0, 1, 2, 3, 4))
                .map { getOutputSignal(it, program) }
                .max() ?: 0
    }

    override fun partTwo(): Any {
        return permutations(listOf(5, 6, 7, 8, 9))
                .map { getOutputSignal(it, program) }
                .max() ?: 0
    }

    fun getOutputSignal(phaseSettings: List<Int>, program: MutableList<Int>): Int {
        val amps = CircularList(*phaseSettings.map { Amplifier(program.toMutableList(), it) }.toTypedArray())
        return generateSequence(0) { amps.next().getOutput(it) }.first { amps.elements.all { it.halted() } }
    }

    inner class Amplifier(program: MutableList<Int>, private val phaseSetting: Int) {

        private val computer = IntcodeComputer(program)

        fun halted() = computer.halted

        fun getOutput(input: Int): Int {
            val output = when {
                computer.waiting -> {
                    computer.restartWithIO(input)
                }
                else -> {
                    computer.runWithIO(stackOf(phaseSetting, input))
                }
            }
            return output.last()
        }
    }
}
