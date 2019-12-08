package common

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.collection.IsEmptyCollection.emptyCollectionOf
import org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder
import org.hamcrest.collection.IsIterableContainingInOrder.contains
import org.hamcrest.core.Is.`is`
import org.junit.Test

class UtilsTest {

    @Test
    fun testStackOf() {
        assertThat(stackOf<Int>(), emptyCollectionOf(Int::class.java))
        val stackOf3 = stackOf(1, 2, 3)
        assertThat(stackOf3, hasSize(3))
        assertThat(stackOf3.pop(), `is`(1))
        assertThat(stackOf3.pop(), `is`(2))
        assertThat(stackOf3.pop(), `is`(3))
    }

    @Test
    fun testPermutations() {
        assertThat(permutations(listOf('a', 'b', 'c')), containsInAnyOrder(
                listOf('a', 'b', 'c'),
                listOf('a', 'c', 'b'),
                listOf('b', 'a', 'c'),
                listOf('b', 'c', 'a'),
                listOf('c', 'a', 'b'),
                listOf('c', 'b', 'a')
        ))
    }

    @Test
    fun testCircularList() {
        val xs = CircularList(1, 2, 3, 4, 5)
        assertThat(xs.elements.toList(), contains(1, 2, 3, 4, 5))
        assertThat(xs.next(), `is`(1))
        assertThat(xs.next(), `is`(2))
        assertThat(xs.next(), `is`(3))
        assertThat(xs.next(), `is`(4))
        assertThat(xs.next(), `is`(5))
        assertThat(xs.next(), `is`(1))
        assertThat(xs.next(), `is`(2))
        assertThat(xs.next(), `is`(3))
        assertThat(xs.next(), `is`(4))
        assertThat(xs.next(), `is`(5))

        assertThat(xs.prev(), `is`(4))
        assertThat(xs.prev(), `is`(3))
        assertThat(xs.prev(), `is`(2))
        assertThat(xs.prev(), `is`(1))
        assertThat(xs.prev(), `is`(5))
        assertThat(xs.prev(), `is`(4))
        assertThat(xs.prev(), `is`(3))
        assertThat(xs.prev(), `is`(2))
        assertThat(xs.prev(), `is`(1))
    }
}
