package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day13Test {

    @Test
    fun testToTiles() {
        val exampleTiles = Day13.ArcadeCabinet.toTiles(listOf(1, 2, 3, 6, 5, 4))
        assertThat(exampleTiles[0].x, `is`(1L))
        assertThat(exampleTiles[0].y, `is`(2L))
        assertThat(exampleTiles[0].tileId, `is`(3L))
        assertThat(exampleTiles[0].tileType, `is`(Day13.TileType.PADDLE))
        assertThat(exampleTiles[1].x, `is`(6L))
        assertThat(exampleTiles[1].y, `is`(5L))
        assertThat(exampleTiles[1].tileId, `is`(4L))
        assertThat(exampleTiles[1].tileType, `is`(Day13.TileType.BALL))

        val scoreTile = Day13.ArcadeCabinet.toTiles(listOf(-1, 0, 99))
        assertThat(scoreTile[0].x, `is`(-1L))
        assertThat(scoreTile[0].y, `is`(0L))
        assertThat(scoreTile[0].tileId, `is`(99L))
        assertThat(scoreTile[0].tileType, `is`(Day13.TileType.SCORE))
    }

    @Test(expected = IllegalArgumentException::class)
    fun testErrorWhenTileIdNotRecognizedAndTileNotScoreTile() {
        Day13.ArcadeCabinet.toTiles(listOf(1, 0, 99))
    }
}
