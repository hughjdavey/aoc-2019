package days

class Day2 : Day(2) {

    private fun program() = inputString.split(",").map { it.trim().toInt() }.toMutableList()

    override fun partOne(): Any {
        return Program(program().toMutableList(), 12, 2).run()
    }

    override fun partTwo(): Any {
        val (noun, verb) = (0..99).flatMap { noun -> (0..99).map { verb -> Pair(noun, verb) }}
                .find { Program(program().toMutableList(), it.first, it.second).run() == 19690720 } ?: Pair(0, 0)
        return 100 * noun + verb
    }

    data class Program(val tape: MutableList<Int>, val noun: Int = tape[1], val verb: Int = tape[2]) {

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
            when (tape[head]) {
                1 -> tape[tape[head + 3]] = tape[tape[head + 1]] + tape[tape[head + 2]]
                2 -> tape[tape[head + 3]] = tape[tape[head + 1]] * tape[tape[head + 2]]
                99 -> halted = true
            }
            head += 4
        }
    }
}
