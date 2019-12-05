package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day4Test {

    @Test
    fun foo() {
        val d4 = Day4()
        assertThat(d4.meetsPasswordCriteria("111111"), `is`(true))
        assertThat(d4.meetsPasswordCriteria("223450"), `is`(false))
        assertThat(d4.meetsPasswordCriteria("123789"), `is`(false))

        assertThat(d4.meetsExtendedPasswordCriteria("112233"), `is`(true))
        assertThat(d4.meetsExtendedPasswordCriteria("123444"), `is`(false))
        assertThat(d4.meetsExtendedPasswordCriteria("111122"), `is`(true))
    }
}
