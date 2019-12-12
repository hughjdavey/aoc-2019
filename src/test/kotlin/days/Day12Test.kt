package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day12Test {

    @Test
    fun testFirstExample() {
        val moonTracker = Day12.MoonTracker(listOf(Day12.Coord3D(-1, 0, 2), Day12.Coord3D(2, -10, -7), Day12.Coord3D(4, -8, 8), Day12.Coord3D(3, 5, -1)))
        assertThat(moonTracker.moons.map { toExampleNotation(it) }, containsInAnyOrder(
                "pos=<x=-1, y=  0, z= 2>, vel=<x= 0, y= 0, z= 0>".replace(Regex("=\\s+"), "="),
                "pos=<x= 2, y=-10, z=-7>, vel=<x= 0, y= 0, z= 0>".replace(Regex("=\\s+"), "="),
                "pos=<x= 4, y= -8, z= 8>, vel=<x= 0, y= 0, z= 0>".replace(Regex("=\\s+"), "="),
                "pos=<x= 3, y=  5, z=-1>, vel=<x= 0, y= 0, z= 0>".replace(Regex("=\\s+"), "=")
        ))

        moonTracker.step()
        assertThat(moonTracker.moons.map { toExampleNotation(it) }, containsInAnyOrder(
                "pos=<x= 2, y=-1, z= 1>, vel=<x= 3, y=-1, z=-1>".replace(Regex("=\\s+"), "="),
                "pos=<x= 3, y=-7, z=-4>, vel=<x= 1, y= 3, z= 3>".replace(Regex("=\\s+"), "="),
                "pos=<x= 1, y=-7, z= 5>, vel=<x=-3, y= 1, z=-3>".replace(Regex("=\\s+"), "="),
                "pos=<x= 2, y= 2, z= 0>, vel=<x=-1, y=-3, z= 1>".replace(Regex("=\\s+"), "=")
        ))

        moonTracker.step()
        assertThat(moonTracker.moons.map { toExampleNotation(it) }, containsInAnyOrder(
                "pos=<x= 5, y=-3, z=-1>, vel=<x= 3, y=-2, z=-2>".replace(Regex("=\\s+"), "="),
                "pos=<x= 1, y=-2, z= 2>, vel=<x=-2, y= 5, z= 6>".replace(Regex("=\\s+"), "="),
                "pos=<x= 1, y=-4, z=-1>, vel=<x= 0, y= 3, z=-6>".replace(Regex("=\\s+"), "="),
                "pos=<x= 1, y=-4, z= 2>, vel=<x=-1, y=-6, z= 2>".replace(Regex("=\\s+"), "=")
        ))

        moonTracker.step()
        assertThat(moonTracker.moons.map { toExampleNotation(it) }, containsInAnyOrder(
                "pos=<x= 5, y=-6, z=-1>, vel=<x= 0, y=-3, z= 0>".replace(Regex("=\\s+"), "="),
                "pos=<x= 0, y= 0, z= 6>, vel=<x=-1, y= 2, z= 4>".replace(Regex("=\\s+"), "="),
                "pos=<x= 2, y= 1, z=-5>, vel=<x= 1, y= 5, z=-4>".replace(Regex("=\\s+"), "="),
                "pos=<x= 1, y=-8, z= 2>, vel=<x= 0, y=-4, z= 0>".replace(Regex("=\\s+"), "=")
        ))

        moonTracker.step()
        assertThat(moonTracker.moons.map { toExampleNotation(it) }, containsInAnyOrder(
                "pos=<x= 2, y=-8, z= 0>, vel=<x=-3, y=-2, z= 1>".replace(Regex("=\\s+"), "="),
                "pos=<x= 2, y= 1, z= 7>, vel=<x= 2, y= 1, z= 1>".replace(Regex("=\\s+"), "="),
                "pos=<x= 2, y= 3, z=-6>, vel=<x= 0, y= 2, z=-1>".replace(Regex("=\\s+"), "="),
                "pos=<x= 2, y=-9, z= 1>, vel=<x= 1, y=-1, z=-1>".replace(Regex("=\\s+"), "=")
        ))

        moonTracker.step()
        assertThat(moonTracker.moons.map { toExampleNotation(it) }, containsInAnyOrder(
                "pos=<x=-1, y=-9, z= 2>, vel=<x=-3, y=-1, z= 2>".replace(Regex("=\\s+"), "="),
                "pos=<x= 4, y= 1, z= 5>, vel=<x= 2, y= 0, z=-2>".replace(Regex("=\\s+"), "="),
                "pos=<x= 2, y= 2, z=-4>, vel=<x= 0, y=-1, z= 2>".replace(Regex("=\\s+"), "="),
                "pos=<x= 3, y=-7, z=-1>, vel=<x= 1, y= 2, z=-2>".replace(Regex("=\\s+"), "=")
        ))

        moonTracker.step()
        assertThat(moonTracker.moons.map { toExampleNotation(it) }, containsInAnyOrder(
                "pos=<x=-1, y=-7, z= 3>, vel=<x= 0, y= 2, z= 1>".replace(Regex("=\\s+"), "="),
                "pos=<x= 3, y= 0, z= 0>, vel=<x=-1, y=-1, z=-5>".replace(Regex("=\\s+"), "="),
                "pos=<x= 3, y=-2, z= 1>, vel=<x= 1, y=-4, z= 5>".replace(Regex("=\\s+"), "="),
                "pos=<x= 3, y=-4, z=-2>, vel=<x= 0, y= 3, z=-1>".replace(Regex("=\\s+"), "=")
        ))

        moonTracker.step()
        assertThat(moonTracker.moons.map { toExampleNotation(it) }, containsInAnyOrder(
                "pos=<x= 2, y=-2, z= 1>, vel=<x= 3, y= 5, z=-2>".replace(Regex("=\\s+"), "="),
                "pos=<x= 1, y=-4, z=-4>, vel=<x=-2, y=-4, z=-4>".replace(Regex("=\\s+"), "="),
                "pos=<x= 3, y=-7, z= 5>, vel=<x= 0, y=-5, z= 4>".replace(Regex("=\\s+"), "="),
                "pos=<x= 2, y= 0, z= 0>, vel=<x=-1, y= 4, z= 2>".replace(Regex("=\\s+"), "=")
        ))

        moonTracker.step()
        assertThat(moonTracker.moons.map { toExampleNotation(it) }, containsInAnyOrder(
                "pos=<x= 5, y= 2, z=-2>, vel=<x= 3, y= 4, z=-3>".replace(Regex("=\\s+"), "="),
                "pos=<x= 2, y=-7, z=-5>, vel=<x= 1, y=-3, z=-1>".replace(Regex("=\\s+"), "="),
                "pos=<x= 0, y=-9, z= 6>, vel=<x=-3, y=-2, z= 1>".replace(Regex("=\\s+"), "="),
                "pos=<x= 1, y= 1, z= 3>, vel=<x=-1, y= 1, z= 3>".replace(Regex("=\\s+"), "=")
        ))

        moonTracker.step()
        assertThat(moonTracker.moons.map { toExampleNotation(it) }, containsInAnyOrder(
                "pos=<x= 5, y= 3, z=-4>, vel=<x= 0, y= 1, z=-2>".replace(Regex("=\\s+"), "="),
                "pos=<x= 2, y=-9, z=-3>, vel=<x= 0, y=-2, z= 2>".replace(Regex("=\\s+"), "="),
                "pos=<x= 0, y=-8, z= 4>, vel=<x= 0, y= 1, z=-2>".replace(Regex("=\\s+"), "="),
                "pos=<x= 1, y= 1, z= 5>, vel=<x= 0, y= 0, z= 2>".replace(Regex("=\\s+"), "=")
        ))

        moonTracker.step()
        assertThat(moonTracker.moons.map { toExampleNotation(it) }, containsInAnyOrder(
                "pos=<x= 2, y= 1, z=-3>, vel=<x=-3, y=-2, z= 1>".replace(Regex("=\\s+"), "="),
                "pos=<x= 1, y=-8, z= 0>, vel=<x=-1, y= 1, z= 3>".replace(Regex("=\\s+"), "="),
                "pos=<x= 3, y=-6, z= 1>, vel=<x= 3, y= 2, z=-3>".replace(Regex("=\\s+"), "="),
                "pos=<x= 2, y= 0, z= 4>, vel=<x= 1, y=-1, z=-1>".replace(Regex("=\\s+"), "=")
        ))

        assertThat(moonTracker.totalEnergy(), `is`(179))
    }

    @Test
    fun testSecondExample() {
        val moonTracker = Day12.MoonTracker(listOf(Day12.Coord3D(-8, -10, 0), Day12.Coord3D(5, 5, 10), Day12.Coord3D(2, -7, 3), Day12.Coord3D(9, -8, -3)))

        moonTracker.simulate(100)
        assertThat(moonTracker.moons.map { toExampleNotation(it) }, containsInAnyOrder(
                "pos=<x=  8, y=-12, z= -9>, vel=<x= -7, y=  3, z=  0>".replace(Regex("=\\s+"), "="),
                "pos=<x= 13, y= 16, z= -3>, vel=<x=  3, y=-11, z= -5>".replace(Regex("=\\s+"), "="),
                "pos=<x=-29, y=-11, z= -1>, vel=<x= -3, y=  7, z=  4>".replace(Regex("=\\s+"), "="),
                "pos=<x= 16, y=-13, z= 23>, vel=<x=  7, y=  1, z=  1>".replace(Regex("=\\s+"), "=")
        ))

        assertThat(moonTracker.totalEnergy(), `is`(1940))
    }

    @Test
    fun testFirstRepeat() {
        val moonTracker = Day12.MoonTracker(listOf(Day12.Coord3D(-1, 0, 2), Day12.Coord3D(2, -10, -7), Day12.Coord3D(4, -8, 8), Day12.Coord3D(3, 5, -1)))
        assertThat(moonTracker.firstRepeat(), `is`(2772L))

        val moonTracker2 = Day12.MoonTracker(listOf(Day12.Coord3D(-8, -10, 0), Day12.Coord3D(5, 5, 10), Day12.Coord3D(2, -7, 3), Day12.Coord3D(9, -8, -3)))
        assertThat(moonTracker2.firstRepeat(), `is`(4686774924L))
    }

    private fun toExampleNotation(moon: Day12.Moon): String {
        val (p, v) = moon.position to moon.velocity
        return "pos=<x=${p.x}, y=${p.y}, z=${p.z}>, vel=<x=${v.x}, y=${v.y}, z=${v.z}>"
    }
}
