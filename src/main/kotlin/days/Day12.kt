package days

import kotlin.math.abs

class Day12 : Day(12) {

    private fun moonPositions() = inputList.map { it.replace(Regex("[<>=xyz ]"), "") }.map {
        val (x, y, z) = it.split(",").map { it.toInt() }
        Coord3D(x, y, z)
    }

    override fun partOne(): Any {
        val moonTracker = MoonTracker(moonPositions())
        moonTracker.simulate(1000)
        return moonTracker.totalEnergy()
    }

    override fun partTwo(): Any {
        val moonTracker = MoonTracker(moonPositions())
        return moonTracker.firstRepeat()
    }

    data class Coord3D(var x: Int, var y: Int, var z: Int) {

        fun addAll(other: Coord3D) {
            this.x += other.x
            this.y += other.y
            this.z += other.z
        }

        fun absSum(): Int = abs(x) + abs(y) + abs(z)
    }

    data class Moon(var position: Coord3D, var velocity: Coord3D = Coord3D(0, 0, 0)) {

        fun totalEnergy(): Int = potentialEnergy() * kineticEnergy()

        private fun potentialEnergy(): Int = position.absSum()

        private fun kineticEnergy(): Int = velocity.absSum()
    }

    class MoonTracker(moonPositions: List<Coord3D>) {

        val moons = moonPositions.map { Moon(it) }.toMutableList()

        private var timeStep = 0

        fun simulate(steps: Int, debug: Boolean = false) {
            if (debug) printState()
            while (timeStep < steps) {
                step()
                if (debug) {
                    printState()
                    System.err.println()
                }
            }
        }

        fun step() {
            // update velocity by applying gravity
            for (i in moons) {
                for (j in moons) {
                    if (i === j) { continue }
                    if (i.position.x < j.position.x) { i.velocity.x++ } else if (i.position.x > j.position.x) { i.velocity.x-- }
                    if (i.position.y < j.position.y) { i.velocity.y++ } else if (i.position.y > j.position.y) { i.velocity.y-- }
                    if (i.position.z < j.position.z) { i.velocity.z++ } else if (i.position.z > j.position.z) { i.velocity.z-- }
                }
            }

            // update position by applying velocity
            moons.forEach { it.position.addAll(it.velocity) }

            timeStep += 1
        }

        fun totalEnergy(): Int {
            return moons.map { it.totalEnergy() }.sum()
        }

        fun firstRepeat(): Long {
            val (initialXPos, initialYPos, initialZPos) = listOf(xPos(), yPos(), zPos())
            val (initialXVel, initialYVel, initialZVel) = listOf(xVel(), yVel(), zVel())
            var (xCycleStep, yCycleStep, zCycleStep) = listOf(-1, -1, -1)
            var steps = 0

            while (xCycleStep == -1 || yCycleStep == -1 || zCycleStep == -1) {
                steps++
                step()

                when {
                    xCycleStep == -1 && initialXPos == xPos() && initialXVel == xVel() -> xCycleStep = steps
                    yCycleStep == -1 && initialYPos == yPos() && initialYVel == yVel() -> yCycleStep = steps
                    zCycleStep == -1 && initialZPos == zPos() && initialZVel == zVel() -> zCycleStep = steps
                }
            }
            return lcm(xCycleStep.toLong(), lcm(yCycleStep.toLong(), zCycleStep.toLong()))
        }

        private fun printState() {
            System.err.println("After $timeStep steps:")
            moons.forEach { System.err.println(it) }
        }

        private fun xPos() = moons.map { it.position.x + 0 }
        private fun yPos() = moons.map { it.position.y + 0 }
        private fun zPos() = moons.map { it.position.z + 0 }

        private fun xVel() = moons.map { it.velocity.x + 0 }
        private fun yVel() = moons.map { it.velocity.y + 0 }
        private fun zVel() = moons.map { it.velocity.z + 0 }

        companion object {

            private fun lcm(x: Long, y: Long): Long {
                var a = x
                var b = y
                while (a != 0L) {
                    a = (b % a).also { b = a }
                }
                return x / b * y
            }
        }
    }
}
