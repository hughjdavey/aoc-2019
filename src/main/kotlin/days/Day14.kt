package days

import kotlin.math.ceil

class Day14 : Day(14) {

    override fun partOne(): Any {
        return NanoFactory(inputList).minimumOreForOneFuel()
    }

    override fun partTwo(): Any {
        return NanoFactory(inputList).maxFuelForOre(ONE_TRILLION)
    }

    data class ChemicalQuantity(val chemical: String, var quantity: Long)

    data class Reaction(val output: ChemicalQuantity, val inputs: List<ChemicalQuantity>)

    class NanoFactory(reactionsList: List<String>) {

        val reactions = reactionsList.map { parseReaction(it) }

        fun minimumOreForOneFuel(): Long {
            return getFuel(1)
        }

        fun maxFuelForOre(maxOre: Long): Long {
            var lo = maxOre / minimumOreForOneFuel()
            var hi = maxOre

            while (lo < hi - 1) {
                val fuelAmount = (lo + hi) / 2
                val ore = getFuel(fuelAmount)
                when {
                    ore < maxOre -> lo = fuelAmount
                    ore == maxOre -> return fuelAmount
                    else -> hi = fuelAmount
                }
            }
            return lo
        }

        private fun getFuel(quantity: Long): Long {
            val have = mutableMapOf<String, Long>()
            var ore = 0L

            fun makeChemical(chemicalQuantity: ChemicalQuantity) {
                if (chemicalQuantity.chemical == "ORE") {
                    ore += chemicalQuantity.quantity
                    return
                }

                val required = chemicalQuantity.quantity - have.getOrDefault(chemicalQuantity.chemical, 0)
                if (required > 0) {
                    val reaction = reactions.find { it.output.chemical == chemicalQuantity.chemical }!!
                    val multiplier = ceil(required.toDouble() / reaction.output.quantity).toInt()
                    for (input in reaction.inputs) {
                        makeChemical(ChemicalQuantity(input.chemical, input.quantity * multiplier))
                    }
                    have[chemicalQuantity.chemical] = have.getOrDefault(chemicalQuantity.chemical, 0) + multiplier * reaction.output.quantity
                }

                have[chemicalQuantity.chemical] = have.getOrDefault(chemicalQuantity.chemical, 0) - chemicalQuantity.quantity
            }

            makeChemical(ChemicalQuantity("FUEL", quantity))
            return ore
        }


        companion object {

            fun parseReaction(str: String): Reaction {
                val (ins, out) = str.split("=>").map { it.trim() }
                return Reaction(parseChemicalQuantity(out), ins.split(',').map { it.trim() }.map { parseChemicalQuantity(it) })
            }

            private fun parseChemicalQuantity(str: String): ChemicalQuantity {
                val (quan, chem) = str.split(' ')
                return ChemicalQuantity(chem, quan.toLong())
            }
        }
    }

    companion object {

        const val ONE_TRILLION = 1_000_000_000_000
    }
}
