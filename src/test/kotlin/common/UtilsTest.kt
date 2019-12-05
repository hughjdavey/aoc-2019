package common

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.collection.IsEmptyCollection.emptyCollectionOf
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
}
