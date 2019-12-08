package days

class Day8 : Day(8) {

    override fun partOne(): Any {
        val min = decodeImage(inputString.trim(), 6, 25).minBy { it.countOf('0') }
        return if (min == null) 0 else min.countOf('1') * min.countOf('2')
    }

    override fun partTwo(): Any {
        // HCGFE
        return "\n" + realImage(inputString.trim(), 6, 25).rows.joinToString("\n") { it.replace('0', ' ').replace('1', '■') }
    }

    fun decodeImage(imageData: String, imageHeight: Int, imageWidth: Int): List<ImageLayer> {
        val layerCount = imageData.length / (imageHeight * imageWidth)
        return imageData.chunked(imageData.length / layerCount).map { ImageLayer(it.chunked(imageWidth)) }
    }

    fun realImage(imageData: String, imageHeight: Int, imageWidth: Int): ImageLayer {
        val layers = decodeImage(imageData, imageHeight, imageWidth)
        val rows = (0 until imageHeight).flatMap { x -> (0 until imageWidth).map { y -> layers.topVisiblePixel(x to y) } }
                .chunked(imageWidth).map { it.fold("") { acc, c -> acc.plus(c) } }
        return ImageLayer(rows)
    }

    data class ImageLayer(val rows: List<String>) {

        fun countOf(char: Char): Int {
            return rows.joinToString("").toList().count { it == char }
        }
    }

    private fun List<ImageLayer>.topVisiblePixel(position: Pair<Int, Int>): Char {
        return this.find { it.rows[position.first][position.second] != '2' }?.rows?.get(position.first)?.get(position.second) ?: 'X'
    }
}
