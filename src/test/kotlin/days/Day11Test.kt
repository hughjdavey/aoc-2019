package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsNull.notNullValue
import org.junit.Test

class Day11Test {

    @Test
    fun testPaintingExample() {
        val robot = Day11().Robot()
        robot.paint()

        // no test input so let's just assert that the first 6 from the example are in there
        assertThat(robot.visited.contains(Day11.Panel(-1 to 1, '#')), `is`(true))
        assertThat(robot.visited.contains(Day11.Panel(0 to 1, '#')), `is`(true))
        assertThat(robot.visited.contains(Day11.Panel(-1 to -1, '#')), `is`(true))
        assertThat(robot.visited.contains(Day11.Panel(0 to -1, '#')), `is`(true))
        assertThat(robot.visited.find { it.coords == 0 to 0 }, notNullValue())
        assertThat(robot.visited.find { it.coords == -1 to 0 }, notNullValue())
    }
}
