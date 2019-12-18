package days

import common.IntcodeComputer
import common.stackOf

class Day17 : Day(17) {

    private fun program() = inputString.split(",").map { it.trim().toLong() }.toMutableList()

    override fun partOne(): Any {
        val image = getImage()
        return getIntersections(image).map { it.alignment }.sum()
    }

    override fun partTwo(): Any {
        val image = getImage()
        val code = getCode(image)
        val program = program()
        program[0] = 2
        val computer = IntcodeComputer(program)

        computer.runWithIO(stackOf(*code.main.toTypedArray()))
        computer.runWithIO(stackOf(*code.a.toTypedArray()))
        computer.runWithIO(stackOf(*code.b.toTypedArray()))
        computer.runWithIO(stackOf(*code.c.toTypedArray()))
        val out = computer.runWithIO(stackOf(*toAscii("n\n").toTypedArray()))
        return out.last()
    }

    fun getImage(cameraOutput: List<Long> = emptyList()): String {
        val output = if (cameraOutput.isNotEmpty()) cameraOutput else {
            val computer = IntcodeComputer(program())
            computer.runWithIO(stackOf())
        }
        return output.fold("") { acc, elem -> acc + elem.toChar() }
    }

    fun withIntersections(image: String): String {
        val rows = image.split('\n')
        val intersections = getIntersections(image)
        return rows.mapIndexed { y, row -> row.mapIndexed { x, char ->
            if (intersections.find { it.coord == x to y } != null) 'O' else char
        }.joinToString("") }.joinToString("\n")
    }

    fun getIntersections(image: String): List<Intersection> {
        val rows = image.split('\n')
        return rows.foldIndexed(listOf()) { y, intersections, row ->
            if (y == 0 || y == row.lastIndex) intersections else {
                val matches = row.windowed(3).mapIndexed { w, s -> if (s.all { it == '#' } && rows[y - 1][w + 1] == '#' && rows[y + 1][w + 1] == '#')
                        Intersection(w + 1 to y) else null }.filterNotNull()
                intersections.plus(matches)
            }
        }
    }

    fun getScaffoldPath(image: String): String {
        val array: Array<Array<Char>> = image.split('\n').filterNot { it.isEmpty() }.map { it.toList().toTypedArray() }.toTypedArray()

        var robot = Robot(RobotDirection.UP, 0 to 0)
        var scaffoldEnd = 0 to 0
        array.forEachIndexed { y, row -> row.forEachIndexed { x, char ->
            if (char == '#' && getNeighbours(x, y, array).filterNot { it == '.' } == listOf('#')) {
                scaffoldEnd = x to y
            }

            if (RobotDirection.values().map { it.value }.contains(char)) {
                robot = Robot(RobotDirection.fromValue(char), x to y)
            }
        } }

        val path = StringBuilder()
        while (robot.position != scaffoldEnd) {
            val lrIndices = when (robot.direction) {
                RobotDirection.UP -> 0 to 1
                RobotDirection.DOWN -> 1 to 0
                RobotDirection.LEFT -> 3 to 2
                RobotDirection.RIGHT -> 2 to 3
            }
            val neighbours = getNeighbours(robot.position.first, robot.position.second, array)
            val (left, right) = listOf(neighbours[lrIndices.first], neighbours[lrIndices.second])
            val goLeft = left == '#'

            robot.turn(goLeft)
            val steps = robot.goUntilTurn(array)
            path.append(if (goLeft) 'L' else 'R').append(',').append(steps).append(',')
        }
        return path.toString().dropLast(1)
    }

    fun getCode(image: String): RobotCode {
        // hardcoded from manual solving - todo make it work for any input
        // scaffoldPath was L,12,L,12,R,12,L,12,L,12,R,12,L,8,L,8,R,12,L,8,L,8,L,10,R,8,R,12,L,10,R,8,R,12,L,12,L,12,R,12,L,8,L,8,R,12,L,8,L,8,L,10,R,8,R,12,L,12,L,12,R,12,L,8,L,8,R,12,L,8,L,8
        return RobotCode("A,A,B,C,C,A,B,C,A,B", "L,12,L,12,R,12", "L,8,L,8,R,12,L,8,L,8", "L,10,R,8,R,12")
    }

    private fun getNeighbours(x: Int, y: Int, array: Array<Array<Char>>): List<Char> {
        val left = if (x == 0) '.' else array[y][x - 1]
        val right = if (x == array[0].lastIndex) '.' else array[y][x + 1]
        val up = if (y == 0) '.' else array[y - 1][x]
        val down = if (y == array.lastIndex) '.' else array[y + 1][x]
        return listOf(left, right, up, down)
    }

    companion object {

        fun toAscii(code: String, addNewline: Boolean = true): List<Long> {
            val ascii = code.toList().map { it.toLong() }
            return if (addNewline) ascii.plus(10) else ascii
        }
    }

    class RobotCode(main: String, a: String, b: String, c: String) {

        val main = toAscii(main)
        val a = toAscii(a)
        val b = toAscii(b)
        val c = toAscii(c)
    }

    data class Robot(var direction: RobotDirection, var position: Pair<Int, Int>) {

        fun goUntilTurn(array: Array<Array<Char>>): Int {
            var next = nextPosition()
            var count = 0
            while (array[next.second][next.first] == '#') {
                position = next
                count++
                next = nextPosition()

                if (next.first < 0 || next.first > array[0].lastIndex || next.second < 0 || next.second > array.lastIndex) {
                    break
                }
            }
            return count
        }

        fun turn(left: Boolean) {
            direction = if (left) direction.turnLeft() else direction.turnRight()
        }

        private fun nextPosition(): Pair<Int, Int> {
            return when (direction) {
                RobotDirection.UP -> position.first to position.second - 1
                RobotDirection.DOWN -> position.first to position.second + 1
                RobotDirection.LEFT -> position.first - 1 to position.second
                RobotDirection.RIGHT -> position.first + 1 to position.second
            }
        }
    }

    enum class RobotDirection(val value: Char) {

        UP('^'), DOWN('v'), LEFT('<'), RIGHT('>');

        fun turnLeft(): RobotDirection {
            return when (this) {
                UP -> LEFT
                DOWN -> RIGHT
                LEFT -> DOWN
                RIGHT -> UP
            }
        }

        fun turnRight(): RobotDirection {
            return when (this) {
                UP -> RIGHT
                DOWN -> LEFT
                LEFT -> UP
                RIGHT -> DOWN
            }
        }

        companion object {
            fun fromValue(value: Char): RobotDirection = values().find { it.value == value } ?: throw IllegalArgumentException("Bad robot state: $value")
        }
    }

    data class Intersection(val coord: Pair<Int, Int>, val alignment: Int = coord.first * coord.second)
}
