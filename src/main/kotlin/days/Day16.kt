package days

import common.CircularList

class Day16 : Day(16) {

    private val signal = inputString.filter { it.isDigit() }.map { it.toString().toInt() }

    private val realSignal = inputString.repeat(10000).filter { it.isDigit() }.map { it.toString().toInt() }

    override fun partOne(): Any {
        return SignalDecoder().phaseUntil(signal, 100).take(8).joinToString("")
    }

    override fun partTwo(): Any {
        return SignalDecoder().phaseUntilReal(realSignal, 100).take(8).joinToString("")
    }

    class SignalDecoder {

        fun phaseUntilReal(startSignal: List<Int>, until: Int): List<Int> {
            val offset = startSignal.take(7).fold("") { acc, elem -> acc + elem }.toInt()
            val signal = startSignal.drop(offset).toMutableList()

            // see https://www.reddit.com/r/adventofcode/comments/ebf5cy/2019_day_16_part_2_understanding_how_to_come_up/
            // see https://www.reddit.com/r/adventofcode/comments/ebf5cy/2019_day_16_part_2_understanding_how_to_come_up/fb5p0hy/
            // todo - integrate this with how we solve part one
            for (n in 1..until) {
                for (i in signal.size - 1 downTo 0) {
                    val next = if (signal.lastIndex >= i + 1) signal[i + 1] else 0
                    signal[i] = (next + signal[i]) % 10
                }
            }
            return signal
        }

        fun phaseUntil(startSignal: List<Int>, until: Int): List<Int> {
            return generateSequence(startSignal) { phase(it) }.take(until + 1).last()
        }

        fun phase(signal: List<Int>): List<Int> {
            return signal.mapIndexed { idx, _ ->
                val patternList = CircularList(*getPattern(idx + 1).toTypedArray())
                patternList.next()
                signal.map { it * patternList.next() }.sum()
            }.map { it.toString().takeLast(1).toInt() }
        }

        fun getPattern(element: Int): List<Int> {
            return BASE_PATTERN.flatMap { i -> List(element) { i } }
        }

        companion object {

            val BASE_PATTERN = listOf(0, 1, 0, -1)
            //val BASE_PATTERN = "0,1,0,-1"
        }
    }
}
