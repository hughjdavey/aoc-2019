package days

import common.IntcodeComputer
import common.IntcodeComputer.Opcode
import common.IntcodeComputer.ParameterMode
import common.stackOf
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

    @Test
    fun testPartOnePrograms() {
        assertThat(IntcodeComputer(mutableListOf(1002, 4, 3, 4, 33)).debug(), contains<Long>(1002, 4, 3, 4, 99))
        assertThat(IntcodeComputer(mutableListOf(1101, 100, -1, 4, 0)).debug(), contains<Long>(1101, 100, -1, 4, 99))
    }

    @Test
    fun testPartTwoSamples() {
        // equal to 8; position mode
        assertThat(IntcodeComputer(mutableListOf(3,9,8,9,10,9,4,9,99,-1,8)).runWithIO(stackOf(4L)), contains<Long>(0))
        assertThat(IntcodeComputer(mutableListOf(3,9,8,9,10,9,4,9,99,-1,8)).runWithIO(stackOf(8L)), contains<Long>(1))

        // less than 8; position mode
        assertThat(IntcodeComputer(mutableListOf(3,9,7,9,10,9,4,9,99,-1,8)).runWithIO(stackOf(4L)), contains<Long>(1))
        assertThat(IntcodeComputer(mutableListOf(3,9,7,9,10,9,4,9,99,-1,8)).runWithIO(stackOf(8L)), contains<Long>(0))
        assertThat(IntcodeComputer(mutableListOf(3,9,7,9,10,9,4,9,99,-1,8)).runWithIO(stackOf(10L)), contains<Long>(0))

        // equal to 8; immediate mode
        assertThat(IntcodeComputer(mutableListOf(3,3,1108,-1,8,3,4,3,99)).runWithIO(stackOf(4L)), contains<Long>(0))
        assertThat(IntcodeComputer(mutableListOf(3,3,1108,-1,8,3,4,3,99)).runWithIO(stackOf(8L)), contains<Long>(1))

        // less than 8; position mode
        assertThat(IntcodeComputer(mutableListOf(3,3,1107,-1,8,3,4,3,99)).runWithIO(stackOf(4L)), contains<Long>(1))
        assertThat(IntcodeComputer(mutableListOf(3,3,1107,-1,8,3,4,3,99)).runWithIO(stackOf(8L)), contains<Long>(0))
        assertThat(IntcodeComputer(mutableListOf(3,3,1107,-1,8,3,4,3,99)).runWithIO(stackOf(10L)), contains<Long>(0))

        // jump 0 if 0 1 if not 0; position mode
        assertThat(IntcodeComputer(mutableListOf(3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9)).runWithIO(stackOf(0L)), contains<Long>(0))
        assertThat(IntcodeComputer(mutableListOf(3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9)).runWithIO(stackOf(1L)), contains<Long>(1))

        // jump 0 if 0 1 if not 0; immediate mode
        assertThat(IntcodeComputer(mutableListOf(3,3,1105,-1,9,1101,0,0,12,4,12,99,1)).runWithIO(stackOf(0L)), contains<Long>(0))
        assertThat(IntcodeComputer(mutableListOf(3,3,1105,-1,9,1101,0,0,12,4,12,99,1)).runWithIO(stackOf(1L)), contains<Long>(1))

        // larger program
        assertThat(IntcodeComputer(
                mutableListOf(3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99))
                .runWithIO(stackOf(7L)), contains<Long>(999))

        assertThat(IntcodeComputer(
                mutableListOf(3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99))
                .runWithIO(stackOf(8L)), contains<Long>(1000))

        assertThat(IntcodeComputer(
                mutableListOf(3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99))
                .runWithIO(stackOf(9L)), contains<Long>(1001))
    }
}
