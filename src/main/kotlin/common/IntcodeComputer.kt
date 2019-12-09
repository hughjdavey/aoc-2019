package common

import java.util.Stack

class IntcodeComputer(private val tape: MutableList<Long>, noun: Long? = null, verb: Long? = null) {

    private var head = 0
    var halted = false
    var waiting = false

    private fun canRun() = !halted && !waiting

    private lateinit var input: Stack<Long>
    private val output = mutableListOf<Long>()

    init {
        if (noun != null && verb != null) {
            tape[1] = noun
            tape[2] = verb
        }
    }

    fun debug(): List<Long> {
        while (canRun()) {
            advance()
        }
        return tape
    }

    fun run(): Long = debug()[0]

    fun runWithIO(input: Stack<Long>): List<Long> {
        this.input = input
        debug()
        return output
    }

    fun restartWithIO(input: Long): List<Long> {
        this.waiting = false
        return runWithIO(stackOf(input))
    }

    private fun advance() {
        val instruction = Instruction(tape[head].toString())
        when (instruction.opcode) {
            Opcode.ADD -> tape[tape[head + 3].toInt()] = instruction.getParam(1, tape, head) + instruction.getParam(2, tape, head)
            Opcode.MUL -> tape[tape[head + 3].toInt()] = instruction.getParam(1, tape, head) * instruction.getParam(2, tape, head)
            Opcode.LESS_THAN -> tape[tape[head + 3].toInt()] = if (instruction.getParam(1, tape, head) < instruction.getParam(2, tape, head)) 1 else 0
            Opcode.EQUALS -> tape[tape[head + 3].toInt()] = if (instruction.getParam(1, tape, head) == instruction.getParam(2, tape, head)) 1 else 0

            Opcode.READ -> {
                if (input.empty()) {
                    this.waiting = true
                }
                else {
                    tape[tape[head + 1].toInt()] = input.pop()
                }
            }
            Opcode.WRITE -> output.add(instruction.getParam(1, tape, head))

            Opcode.JUMP_TRUE -> head = if (instruction.getParam(1, tape, head) != 0L) instruction.getParam(2, tape, head).toInt() else head + instruction.numberOfArgs() + 1
            Opcode.JUMP_FALSE -> head = if (instruction.getParam(1, tape, head) == 0L) instruction.getParam(2, tape, head).toInt() else head + instruction.numberOfArgs() + 1

            Opcode.HALT -> halted = true
        }

        if (!instruction.modifiesInstructionPointer() && canRun()) {
            head += instruction.numberOfArgs() + 1
        }
    }

    class Instruction(instruction: String) {

        val opcode: Opcode
        val parameterModes: List<ParameterMode>

        init {
            if (instruction.isEmpty()) {
                throw IllegalArgumentException()
            }
            opcode = Opcode.fromValue(instruction.takeLast(2).toInt())

            val modes = instruction.dropLast(2).reversed().takeLast(3).padEnd(numberOfArgs(), '0')
            parameterModes = modes.map { ParameterMode.fromValue(it.toString().toInt()) }
        }

        fun getParam(param: Int, tape: MutableList<Long>, head: Int): Long {
            val paramMode = parameterModes[param - 1]
            return if (paramMode == ParameterMode.IMMEDIATE) tape[head + param] else tape[tape[head + param].toInt()]
        }

        fun numberOfArgs(): Int {
            return when (opcode) {
                Opcode.ADD, Opcode.MUL, Opcode.LESS_THAN, Opcode.EQUALS -> 3
                Opcode.JUMP_TRUE, Opcode.JUMP_FALSE -> 2
                Opcode.READ, Opcode.WRITE -> 1
                else -> 0
            }
        }

        fun modifiesInstructionPointer() = opcode == Opcode.JUMP_TRUE || opcode == Opcode.JUMP_FALSE
    }

    enum class Opcode(val value: Int) {

        ADD(1), MUL(2), READ(3), WRITE(4), JUMP_TRUE(5), JUMP_FALSE(6), LESS_THAN(7), EQUALS(8), HALT(99);

        companion object {
            fun fromValue(value: Int): Opcode = values().find { it.value == value } ?: throw IllegalArgumentException("Bad opcode: $value")
        }
    }

    enum class ParameterMode(val value: Int) {

        POSITION(0), IMMEDIATE(1);

        companion object {
            fun fromValue(value: Int): ParameterMode = values().find { it.value == value } ?: throw IllegalArgumentException("Bad mode: $value")
        }
    }
}
