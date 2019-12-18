package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder
import org.hamcrest.collection.IsIterableContainingInOrder.contains
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day16Test {

    @Test
    fun testGettingPattern() {
        val decoder = Day16.SignalDecoder()
        assertThat(decoder.getPattern(3), contains(0, 0, 0, 1, 1, 1, 0, 0, 0, -1, -1, -1))
    }

    @Test
    fun testPhase() {
        val decoder = Day16.SignalDecoder()
        assertThat(decoder.phase(listOf(1, 2, 3, 4, 5, 6, 7, 8)), contains(4, 8, 2, 2, 6, 1, 5, 8))
        assertThat(decoder.phase(listOf(4, 8, 2, 2, 6, 1, 5, 8)), contains(3, 4, 0, 4, 0, 4, 3, 8))
        assertThat(decoder.phase(listOf(3, 4, 0, 4, 0, 4, 3, 8)), contains(0, 3, 4, 1, 5, 5, 1, 8))
        assertThat(decoder.phase(listOf(0, 3, 4, 1, 5, 5, 1, 8)), contains(0, 1, 0, 2, 9, 4, 9, 8))
    }

    @Test
    fun testPhaseUntil() {
        val decoder = Day16.SignalDecoder()
        assertThat(decoder.phaseUntil("80871224585914546619083218645595".map { it.toString().toInt() }, 100).take(8), contains(2, 4, 1, 7, 6, 1, 7, 6))
        assertThat(decoder.phaseUntil("19617804207202209144916044189917".map { it.toString().toInt() }, 100).take(8), contains(7, 3, 7, 4, 5, 4, 1, 8))
        assertThat(decoder.phaseUntil("69317163492948606335995924319873".map { it.toString().toInt() }, 100).take(8), contains(5, 2, 4, 3, 2, 1, 3, 3))
    }

    @Test
    fun foo() {
        val decoder = Day16.SignalDecoder()
        assertThat(decoder.phaseUntilReal("03036732577212944063491565474664".repeat(10000).map { it.toString().toInt() }, 100).take(8), contains(8, 4, 4, 6, 2, 0, 2, 6))
        assertThat(decoder.phaseUntilReal("02935109699940807407585447034323".repeat(10000).map { it.toString().toInt() }, 100).take(8), contains(7, 8, 7, 2, 5, 2, 7, 0))
        assertThat(decoder.phaseUntilReal("03081770884921959731165446850517".repeat(10000).map { it.toString().toInt() }, 100).take(8), contains(5, 3, 5, 5, 3, 7, 3, 1))
    }
}
