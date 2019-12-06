package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day6Test {

    @Test
    fun testPartOneExample() {
        assertThat(Day6().numberOfOrbits(listOf("COM)B", "B)C", "C)D", "D)E", "E)F", "B)G", "G)H", "D)I", "E)J", "J)K", "K)L")), `is`(42))
    }

    @Test
    fun testPartTwoExample() {
        assertThat(Day6().numberOfOrbits(listOf("COM)B", "B)C", "C)D", "D)E", "E)F", "B)G", "G)H", "D)I", "E)J", "J)K", "K)L", "K)YOU", "I)SAN" ), "YOU", "SAN"), `is`(4))
    }
}
