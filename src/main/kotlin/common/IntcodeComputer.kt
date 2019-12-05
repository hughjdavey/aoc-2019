package common

class IntcodeComputer(val tape: MutableList<Int>, val noun: Int = tape[1], val verb: Int = tape[2]) {

    private var head = 0
    private var halted = false

    init {
        tape[1] = noun
        tape[2] = verb
    }

    fun debug(): List<Int> {
        while (!halted) {
            advance()
        }
        return tape
    }

    fun run(): Int = debug()[0]

    private fun advance() {
        val instruction = Instruction(tape[head].toString())
        when (instruction.opcode) {
            Opcode.ADD -> tape[tape[head + 3]] = tape[tape[head + 1]] + tape[tape[head + 2]]
            Opcode.MUL -> tape[tape[head + 3]] = tape[tape[head + 1]] * tape[tape[head + 2]]
            Opcode.READ -> tape[tape[head + 1]] = System.console().readLine().toInt()
            Opcode.WRITE -> println(tape[tape[head + 1]])
            Opcode.HALT -> halted = true
        }
        head += instruction.opcode.numberOfArgs() + 1
    }

    class Instruction(instruction: String) {

        val opcode: Opcode
        val parameterModes: List<ParameterMode>

        init {
            if (instruction.isEmpty()) {
                throw IllegalArgumentException()
            }
            opcode = Opcode.fromValue(instruction.takeLast(2).toInt())

            val modes = instruction.dropLast(2).reversed().takeLast(3).padEnd(opcode.numberOfArgs(), '0')
            parameterModes = modes.map { ParameterMode.fromValue(it.toString().toInt()) }
        }
    }

    enum class Opcode(val value: Int) {

        ADD(1), MUL(2), READ(3), WRITE(4), HALT(99);

        fun numberOfArgs(): Int {
            return when (this) {
                ADD, MUL -> 3
                READ, WRITE -> 1
                else -> 0
            }
        }

        companion object {
            fun fromValue(value: Int): Opcode = values().find { it.value == value } ?: throw IllegalArgumentException()
        }
    }

    enum class ParameterMode(val value: Int) {

        POSITION(0), IMMEDIATE(1);

        companion object {
            fun fromValue(value: Int): ParameterMode = values().find { it.value == value } ?: throw IllegalArgumentException()
        }
    }
}


