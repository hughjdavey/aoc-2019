package days

class Day1 : Day(1) {

    override fun partOne(): Any {
        return inputList.map { Module(it.toInt()) }
                .map { it.fuelRequired() }.sum()
    }

    override fun partTwo(): Any {
        return inputList.map { Module(it.toInt()) }
                .map { it.fuelRequiredIncludingSelf() }.sum()
    }

    data class Module(val mass: Int) {

        fun fuelRequired(): Int {
            return (mass / 3) - 2
        }

        fun fuelRequiredIncludingSelf(prev: Module = this): Int {
            val prevFuel = prev.fuelRequired()
            return if (prevFuel <= 0) 0 else {
                fuelRequiredIncludingSelf(Module(prevFuel)) + prevFuel
            }
        }
    }
}
