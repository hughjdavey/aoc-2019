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

    @Test
    fun testDistance() {
        assertThat(Day10.AsteroidGrid.distance(8 to 3, 8 to 1), `is`(2))
        assertThat(Day10.AsteroidGrid.distance(8 to 3, 8 to 0), `is`(3))
        assertThat(Day10.AsteroidGrid.distance(8 to 1, 8 to 3), `is`(2))
        assertThat(Day10.AsteroidGrid.distance(8 to 0, 8 to 3), `is`(3))
    }

    @Test
    fun testVaporization() {
        val grid = Day10.AsteroidGrid(listOf(
                ".#....#####...#..",
                "##...##.#####..##",
                "##...#...#.#####.",
                "..#.....#...###..",
                "..#.#.....#....##"
        ))

        assertThat(grid.bestLocationForMonitoring(), `is`(8 to 3))
        assertThat(grid.vaporizedNth(9), `is`(15 to 1))
        assertThat(grid.vaporizedNth(10), `is`(12 to 2))
        assertThat(grid.vaporizedNth(14), `is`(12 to 3))
        assertThat(grid.vaporizedNth(19), `is`(2 to 4))
    }

    @Test
    fun testVaporizationBigGrid() {
        val grid = Day10.AsteroidGrid(listOf(
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
        assertThat(grid.bestLocationForMonitoring(), `is`(11 to 13))

        assertThat(grid.vaporizedNth(1), `is`(11 to 12))
        assertThat(grid.vaporizedNth(2), `is`(12 to 1))
        assertThat(grid.vaporizedNth(3), `is`(12 to 2))
        assertThat(grid.vaporizedNth(10), `is`(12 to 8))
        assertThat(grid.vaporizedNth(20), `is`(16 to 0))
        assertThat(grid.vaporizedNth(50), `is`(16 to 9))
        assertThat(grid.vaporizedNth(100), `is`(10 to 16))
        assertThat(grid.vaporizedNth(199), `is`(9 to 6))
        assertThat(grid.vaporizedNth(200), `is`(8 to 2))
        assertThat(grid.vaporizedNth(201), `is`(10 to 9))
        assertThat(grid.vaporizedNth(299), `is`(11 to 1))
    }
}
