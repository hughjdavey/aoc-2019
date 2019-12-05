import common.IntcodeComputer
import common.IntcodeComputer.Opcode
import common.IntcodeComputer.ParameterMode
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.collection.IsEmptyCollection.empty
import org.hamcrest.collection.IsIterableContainingInOrder.contains
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day5Test {

    @Test
    fun testParseInstruction() {
        // add instruction as in day 2: 3 args all positional
        val day2Add = IntcodeComputer.Instruction("1")
        assertThat(day2Add.opcode, `is`(Opcode.ADD))
        assertThat(day2Add.parameterModes, contains(ParameterMode.POSITION, ParameterMode.POSITION, ParameterMode.POSITION))

        // mul instruction as in day 2: 3 args all positional
        val day2Mul = IntcodeComputer.Instruction("2")
        assertThat(day2Mul.opcode, `is`(Opcode.MUL))
        assertThat(day2Mul.parameterModes, contains(ParameterMode.POSITION, ParameterMode.POSITION, ParameterMode.POSITION))

        // read instruction as in day 5: 1 arg positional
        val day5Read = IntcodeComputer.Instruction("3")
        assertThat(day5Read.opcode, `is`(Opcode.READ))
        assertThat(day5Read.parameterModes, contains(ParameterMode.POSITION))

        // write instruction as in day 5: 1 arg positional
        val day5Write = IntcodeComputer.Instruction("4")
        assertThat(day5Write.opcode, `is`(Opcode.WRITE))
        assertThat(day5Write.parameterModes, contains(ParameterMode.POSITION))

        // mul instruction as in day 2: 3 args second immediate
        val day5Mul = IntcodeComputer.Instruction("1002")
        assertThat(day5Mul.opcode, `is`(Opcode.MUL))
        assertThat(day5Mul.parameterModes, contains(ParameterMode.POSITION, ParameterMode.IMMEDIATE, ParameterMode.POSITION))

        // halt instruction as in day 2: 3 args all positional
        val day2Halt = IntcodeComputer.Instruction("99")
        assertThat(day2Halt.opcode, `is`(Opcode.HALT))
        assertThat(day2Halt.parameterModes, empty())
    }
}
