import days.Day3
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day3Test {

    @Test
    fun testWireGrid() {
        val d3 = Day3()
        assertThat(d3.closestIntersectionSum("R8,U5,L5,D3", "U7,R6,D4,L4"), `is`(6))
        assertThat(d3.closestIntersectionSum("R75,D30,R83,U83,L12,D49,R71,U7,L72", "U62,R66,U55,R34,D71,R55,D58,R83"), `is`(159))
        assertThat(d3.closestIntersectionSum("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51", "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"), `is`(135))

        assertThat(d3.fewestSteps("R8,U5,L5,D3", "U7,R6,D4,L4"), `is`(30))
        assertThat(d3.fewestSteps("R75,D30,R83,U83,L12,D49,R71,U7,L72", "U62,R66,U55,R34,D71,R55,D58,R83"), `is`(610))
        assertThat(d3.fewestSteps("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51", "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"), `is`(410))
    }
}
