package days

import common.IntcodeComputer
import org.junit.Test
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.core.Is.`is`

class Day9Test {

    fun testExamplePrograms() {
        assertThat(IntcodeComputer(mutableListOf(109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99)).debug(), `is`(listOf<Long>(109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99)))
        assertThat(IntcodeComputer(mutableListOf(1102,34915192,34915192,7,4,7,99,0)).debug(), `is`(listOf<Long>(0)))
        assertThat(IntcodeComputer(mutableListOf(104,1125899906842624,99)).debug(), `is`(listOf(1125899906842624)))
    }

}
