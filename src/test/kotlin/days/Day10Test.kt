package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day10Test {

    @Test
    fun testFirstExampleAsteroidGridThoroughly() {
        val grid = Day10.AsteroidGrid(listOf(
                ".#..#",
                ".....",
                "#####",
                "....#",
                "...##"
        ))

        assertThat(grid.get(0, 0), `is`('.'))
        assertThat(grid.get(2, 2), `is`('#'))
        assertThat(grid.get(4, 4), `is`('#'))

        assertThat(grid.allAsteroids(), hasSize(10))
        assertThat(grid.allAsteroids(), containsInAnyOrder(
                1 to 0, 4 to 0, 0 to 2, 1 to 2, 2 to 2, 3 to 2, 4 to 2, 4 to 3, 3 to 4, 4 to 4
        ))

        assertThat(grid.numberCanDetect(1, 0), `is`(7))
        assertThat(grid.numberCanDetect(4, 0), `is`(7))

        assertThat(grid.numberCanDetect(0, 2), `is`(6))
        assertThat(grid.numberCanDetect(1, 2), `is`(7))
        assertThat(grid.numberCanDetect(2, 2), `is`(7))
        assertThat(grid.numberCanDetect(3, 2), `is`(7))
        assertThat(grid.numberCanDetect(4, 2), `is`(5))

        assertThat(grid.numberCanDetect(4, 3), `is`(7))

        assertThat(grid.numberCanDetect(3, 4), `is`(8))
        assertThat(grid.numberCanDetect(4, 4), `is`(7))

        assertThat(grid.bestLocationForMonitoring(), `is`(3 to 4))
    }

    @Test
    fun testRemainingAsteroidGrids() {
        val ex1 = Day10.AsteroidGrid(listOf(
                "......#.#.",
                "#..#.#....",
                "..#######.",
                ".#.#.###..",
                ".#..#.....",
                "..#....#.#",
                "#..#....#.",
                ".##.#..###",
                "##...#..#.",
                ".#....####"
        ))
        assertThat(ex1.bestLocationForMonitoring(), `is`(5 to 8))
        assertThat(ex1.numberCanDetect(5, 8), `is`(33))

        val ex2 = Day10.AsteroidGrid(listOf(
                "#.#...#.#.",
                ".###....#.",
                ".#....#...",
                "##.#.#.#.#",
                "....#.#.#.",
                ".##..###.#",
                "..#...##..",
                "..##....##",
                "......#...",
                ".####.###."
        ))
        assertThat(ex2.bestLocationForMonitoring(), `is`(1 to 2))
        assertThat(ex2.numberCanDetect(1, 2), `is`(35))

        val ex3 = Day10.AsteroidGrid(listOf(
                ".#..#..###",
                "####.###.#",
                "....###.#.",
                "..###.##.#",
                "##.##.#.#.",
                "....###..#",
                "..#.#..#.#",
                "#..#.#.###",
                ".##...##.#",
                ".....#.#.."
        ))
        assertThat(ex3.bestLocationForMonitoring(), `is`(6 to 3))
        assertThat(ex3.numberCanDetect(6, 3), `is`(41))

        val ex4 = Day10.AsteroidGrid(listOf(
                ".#..##.###...#######",
                "##.############..##.",
                ".#.######.########.#",
                ".###.#######.####.#.",
                "#####.##.#.##.###.##",
                "..#####..#.#########",
                "####################",
                "#.####....###.#.#.##",
                "##.#################",
                "#####.##.###..####..",
                "..######..##.#######",
                "####.##.####...##..#",
                ".#####..#.######.###",
                "##...#.##########...",
                "#.##########.#######",
                ".####.#.###.###.#.##",
                "....##.##.###..#####",
                ".#.#.###########.###",
                "#.#.#.#####.####.###",
                "###.##.####.##.#..##"
        ))
        assertThat(ex4.bestLocationForMonitoring(), `is`(11 to 13))
        assertThat(ex4.numberCanDetect(11, 13), `is`(210))
    }
}
