package days

import common.IntcodeComputer
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day2Test {

    @Test
    fun testProgram() {
        assertThat(IntcodeComputer(mutableListOf(1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50)).debug(), `is`(listOf(3500, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50)))
        assertThat(IntcodeComputer(mutableListOf(1, 0, 0, 0, 99)).debug(), `is`(listOf(2, 0, 0, 0, 99)))
        assertThat(IntcodeComputer(mutableListOf(2, 3, 0, 3, 99)).debug(), `is`(listOf(2, 3, 0, 6, 99)))
        assertThat(IntcodeComputer(mutableListOf(2, 4, 4, 5, 99, 0)).debug(), `is`(listOf(2, 4, 4, 5, 99, 9801)))
        assertThat(IntcodeComputer(mutableListOf(1, 1, 1, 4, 99, 5, 6, 0, 99)).debug(), `is`(listOf(30, 1, 1, 4, 2, 5, 6, 0, 99)))
    }
}
