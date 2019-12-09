package days

import common.IntcodeComputer
import common.stackOf
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day9Test {

    @Test
    fun testExamplePrograms() {
        assertThat(IntcodeComputer(mutableListOf(109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99)).runWithIO(stackOf()), `is`(listOf<Long>(109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99)))
        assertThat(IntcodeComputer(mutableListOf(1102,34915192,34915192,7,4,7,99,0)).runWithIO(stackOf()).last(), `is`(1219070632396864L))
        assertThat(IntcodeComputer(mutableListOf(104,1125899906842624,99)).runWithIO(stackOf()).last(), `is`(1125899906842624L))
    }
}
