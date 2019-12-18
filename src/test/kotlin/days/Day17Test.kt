package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.collection.IsIterableContainingInOrder.contains
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day17Test {

    @Test
    fun testPartOneExample() {
        val exampleImage = listOf<Long>(
                46, 46, 35, 46, 46, 46, 46, 46, 46, 46, 46, 46, 46, 10,
                46, 46, 35, 46, 46, 46, 46, 46, 46, 46, 46, 46, 46, 10,
                35, 35, 35, 35, 35, 35, 35, 46, 46, 46, 35, 35, 35, 10,
                35, 46, 35, 46, 46, 46, 35, 46, 46, 46, 35, 46, 35, 10,
                35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 10,
                46, 46, 35, 46, 46, 46, 35, 46, 46, 46, 35, 46, 46, 10,
                46, 46, 35, 35, 35, 35, 35, 46, 46, 46, 94, 46, 46
        )

        val image = Day17().getImage(exampleImage)
        assertThat(image, `is`("""
                ..#..........
                ..#..........
                #######...###
                #.#...#...#.#
                #############
                ..#...#...#..
                ..#####...^..
        """.trimIndent()))

        val intersections = Day17().getIntersections(image)
        assertThat(intersections.map { it.alignment }, contains(4, 8, 24, 40))
        assertThat(intersections.map { it.alignment }.sum(), `is`(76))

        val imageWithIntersections = Day17().withIntersections(image)
        assertThat(imageWithIntersections, `is`("""
                ..#..........
                ..#..........
                ##O####...###
                #.#...#...#.#
                ##O###O###O##
                ..#...#...#..
                ..#####...^..
        """.trimIndent()))
    }

    @Test
    fun testGetScaffoldPath() {
        val image = """
            #######...#####
            #.....#...#...#
            #.....#...#...#
            ......#...#...#
            ......#...###.#
            ......#.....#.#
            ^########...#.#
            ......#.#...#.#
            ......#########
            ........#...#..
            ....#########..
            ....#...#......
            ....#...#......
            ....#...#......
            ....#####......
        """.trimIndent()

        val path = Day17().getScaffoldPath(image)
        assertThat(path, `is`("R,8,R,8,R,4,R,4,R,8,L,6,L,2,R,4,R,4,R,8,R,8,R,8,L,6,L,2"))
    }

    @Test
    fun testToAscii() {
        assertThat(Day17.toAscii("A,B,C,B,A,C"), `is`(listOf<Long>(65, 44, 66, 44, 67, 44, 66, 44, 65, 44, 67, 10)))
        assertThat(Day17.toAscii("R,8,R,8"), `is`(listOf<Long>(82, 44, 56, 44, 82, 44, 56, 10)))
        assertThat(Day17.toAscii("R,4,R,4,R,8"), `is`(listOf<Long>(82, 44, 52, 44, 82, 44, 52, 44, 82, 44, 56, 10)))
        assertThat(Day17.toAscii("L,6,L,2"), `is`(listOf<Long>(76, 44, 54, 44, 76, 44, 50, 10)))

        // test numbers over 10
        assertThat(Day17.toAscii("L,10,R,8,R,12"), `is`(listOf<Long>(76, 44, 49, 48, 44, 82, 44, 56, 44, 82, 44, 49, 50, 10)))
    }
}
