package days

import kotlin.math.abs
import kotlin.math.atan2

class Day10 : Day(10) {

    private val grid = AsteroidGrid(inputList)

    override fun partOne(): Any {
        val bestLocation = grid.bestLocationForMonitoring()
        val numberDetectable = grid.numberCanDetect(bestLocation.first, bestLocation.second)
        return "Best is $bestLocation with $numberDetectable other asteroids detected"
    }

    override fun partTwo(): Any {
        val vaporized200th = grid.vaporizedNth(200)
        return (vaporized200th.first * 100) + vaporized200th.second
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

        fun vaporizedNth(n: Int): Pair<Int, Int> {
            val location = bestLocationForMonitoring()

            val asteroidsToAngles = allAsteroids().filter { it != location }.map { asteroid ->
                val deltaX = asteroid.first.toDouble() - location.first.toDouble()
                val deltaY = asteroid.second.toDouble() - location.second.toDouble()
                val angle = ( atan2(deltaY, deltaX) * 180 / Math.PI ) + 90
                asteroid to if (angle < 0) angle + 360 else angle
            }.sortedByDescending { it.second }.reversed()

            // todo clean up
            var aa = asteroidsToAngles
            val destroyed = mutableListOf<Pair<Int, Int>>()

            while (destroyed.size < n) {
                val (remaining, gone) = doRotation(aa, location)
                destroyed.addAll(gone.map { it.first })
                aa = remaining
            }
            return destroyed[n - 1]
        }

        // todo clean up
        private fun doRotation(asteroidsToAngles: List<Pair<Pair<Int, Int>, Double>>, location: Pair<Int, Int>):
                Pair< List<Pair<Pair<Int, Int>, Double>>, List<Pair<Pair<Int, Int>, Double>> > {
            val pass = asteroidsToAngles.filter { a2a ->
                val allWithSameAngle = asteroidsToAngles.filter { it.second == a2a.second }
                if (allWithSameAngle.size == 1) {
                    true
                }
                else {
                    val distance = distance(location, a2a.first)
                    distance == allWithSameAngle.map { distance(location, it.first) }.min() ?: 0
                }
            }
            return asteroidsToAngles.filter { a2a -> pass.find { it.first == a2a.first } == null } to
                    asteroidsToAngles.filter { a2a -> pass.find { it.first == a2a.first } != null }
        }

        private fun isAsteroid(x: Int, y: Int): Boolean = get(x, y) == '#'

        companion object {

            fun distance(a1: Pair<Int, Int>, a2: Pair<Int, Int>): Int {
                return abs(a2.second - a1.second) + abs(a2.first - a1.first)
            }
        }
    }
}
