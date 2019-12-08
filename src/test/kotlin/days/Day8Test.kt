package days

import org.junit.Test
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.core.Is.`is`

class Day8Test {

    @Test
    fun testDecodingImage() {
        val layers = Day8().decodeImage("123456789012", 2, 3)
        assertThat(layers, hasSize(2))
        assertThat(layers[0], `is`(Day8.ImageLayer(listOf("123", "456"))))
        assertThat(layers[1], `is`(Day8.ImageLayer(listOf("789", "012"))))
    }

    @Test
    fun testRealImage() {
        assertThat(Day8().realImage("0222112222120000", 2, 2), `is`(Day8.ImageLayer(listOf("01", "10"))))
    }
}
