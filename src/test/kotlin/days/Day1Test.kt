package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day1Test {

    @Test
    fun testFuelRequired() {
        assertThat(Day1.Module(12).fuelRequired(), `is`(2))
        assertThat(Day1.Module(14).fuelRequired(), `is`(2))
        assertThat(Day1.Module(1969).fuelRequired(), `is`(654))
        assertThat(Day1.Module(100756).fuelRequired(), `is`(33583))
    }

    @Test
    fun testFuelRequiredInlcudingSelf() {
        assertThat(Day1.Module(14).fuelRequiredIncludingSelf(), `is`(2))
        assertThat(Day1.Module(1969).fuelRequiredIncludingSelf(), `is`(966))
        assertThat(Day1.Module(100756).fuelRequiredIncludingSelf(), `is`(50346))
    }
}
