package days

import kotlin.math.atan2

class Day10 : Day(10) {

    override fun partOne(): Any {
        val grid = AsteroidGrid(inputList)
        val bestLocation = grid.bestLocationForMonitoring()
        val numberDetectable = grid.numberCanDetect(bestLocation.first, bestLocation.second)
        return "Best is $bestLocation with $numberDetectable other asteroids detected"
    }

    override fun partTwo(): Any {
        return 0
    }

    class AsteroidGrid(private val rows: List<String>) {

        fun get(x: Int, y: Int): Char {
            return rows[y][x]
        }

        fun numberCanDetect(x: Int, y: Int): Int {
            val angles = allAsteroids().filter { it != x to y }.map { asteroid ->
                val deltaX = asteroid.first.toDouble() - x.toDouble()
                val deltaY = asteroid.second.toDouble() - y.toDouble()
                atan2(deltaY, deltaX) * 180 / Math.PI
            }
            return angles.toSet().size
        }

        fun allAsteroids(): List<Pair<Int, Int>> {
            return (0..rows.lastIndex).flatMap { y -> (0..rows[0].lastIndex).map { x -> if (isAsteroid(x, y)) x to y else null } }.filterNotNull()
        }

        fun bestLocationForMonitoring(): Pair<Int, Int> {
            return allAsteroids().maxBy { numberCanDetect(it.first, it.second) } ?: 0 to 0
        }

        private fun isAsteroid(x: Int, y: Int): Boolean = get(x, y) == '#'
    }
}
