package days

import common.IntcodeComputer
import common.stackOf

class Day13 : Day(13) {

    private fun program() = inputString.split(",").map { it.trim().toLong() }.toMutableList()

    override fun partOne(): Any {
        val arcadeCabinet = ArcadeCabinet(program())
        return arcadeCabinet.runGame().count { it.tileType == TileType.BLOCK }
    }

    // todo perhaps refactor as takes ~1.9s to run
    override fun partTwo(): Any {
        val program = program()
        program[0] = 2L
        val arcadeCabinet = ArcadeCabinet(program)
        return arcadeCabinet.runGameWithJoystick()
    }

    enum class TileType { EMPTY, WALL, BLOCK, PADDLE, BALL, SCORE }

    data class GameTile(val x: Long, val y: Long, val tileId: Long) {

        val tileType = when (tileId) {
            0L -> TileType.EMPTY
            1L -> TileType.WALL
            2L -> TileType.BLOCK
            3L -> TileType.PADDLE
            4L -> TileType.BALL
            else -> {
                if (!isScoreTile()) {
                    throw IllegalArgumentException("Unknown Tile $tileId")
                }
                TileType.SCORE
            }
        }

        private fun isScoreTile() = x == -1L && y == 0L
    }

    class ArcadeCabinet(program: MutableList<Long>) {

        private val computer = IntcodeComputer(program)

        fun runGame(): List<GameTile> {
            val output = computer.runWithIO(stackOf())
            return toTiles(output)
        }

        fun runGameWithJoystick(): Long {
            var tiles = toTiles(computer.runWithIO(stackOf()))
            while (!computer.halted) {
                val ball = tiles.findLast { it.tileType == TileType.BALL }!!
                val paddle = tiles.findLast { it.tileType == TileType.PADDLE }!!

                val joystickInput = when {
                    ball.x > paddle.x -> 1L
                    ball.x < paddle.x -> -1L
                    else -> 0L
                }

                tiles = toTiles(computer.runWithIO(joystickInput))
            }
            return tiles.findLast { it.tileType == TileType.SCORE }!!.tileId
        }

        companion object {
            fun toTiles(output: List<Long>) = output.chunked(3).map { GameTile(it[0], it[1], it[2]) }
        }
    }
}
