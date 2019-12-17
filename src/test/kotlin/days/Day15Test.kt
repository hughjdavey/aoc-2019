package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.collection.IsIterableContainingInOrder.contains
import org.junit.Test

class Day15Test {

    @Test
    fun testDirectionsFromStart() {
        assertThat(Day15().RepairDroid().possibleDirections(), contains(Day15.Direction.WEST))
    }
}
