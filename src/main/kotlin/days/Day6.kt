package days

class Day6 : Day(6) {

    override fun partOne(): Any {
        return numberOfOrbits(inputList)
    }

    override fun partTwo(): Any {
        return numberOfOrbits(inputList, "YOU", "SAN")
    }

    fun numberOfOrbits(map: List<String>): Int {
        val orbits = orbits(map)
        return orbits.keys.map { count(it, orbits) }.sum()
    }

    fun numberOfOrbits(map: List<String>, start: String, dest: String): Int {
        val orbits = orbits(map)
        val (startChain, destChain) = backChain(start, orbits) to backChain(dest, orbits)
        val firstIntersection = startChain.first { planet -> destChain.contains(planet) }
        return startChain.indexOf(firstIntersection) + destChain.indexOf(firstIntersection)
    }

    private fun orbits(map: List<String>): Map<String, String> {
        return map.map { orbit ->
            val (orbitee, orbiter) = orbit.split(')')
            orbiter to orbitee
        }.toMap()
    }

    private fun count(planet: String, planets: Map<String, String>): Int {
        val orbitee = planets[planet]
        return if (orbitee == null) 0 else {
            1 + count(orbitee, planets)
        }
    }

    private fun backChain(planet: String, planets: Map<String, String>): List<String> {
        val orbitee = planets[planet]
        return if (orbitee == null) emptyList() else {
            listOf(orbitee).plus(backChain(orbitee, planets))
        }
    }
}
