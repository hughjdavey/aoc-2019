package days

class Day4 : Day(4) {

    private val range = inputString.split('-').map { it.trim().toInt() }

    override fun partOne(): Any {
        return (range[0]..range[1]).count { meetsPasswordCriteria(it.toString()) }
    }

    override fun partTwo(): Any {
        return (range[0]..range[1]).count { meetsExtendedPasswordCriteria(it.toString()) }
    }

    fun meetsPasswordCriteria(password: String): Boolean {
        return password.length == 6 && password.all { it.isDigit() } && password.toSet().size < 6 &&
                password.map { it.toInt() }.windowed(2, 1).all { it[1] >= it[0] }
    }

    fun meetsExtendedPasswordCriteria(password: String): Boolean {
        return meetsPasswordCriteria(password) &&
                "xx${password}xx".windowed(4, 1).find { it[1] == it[2] && it[0] != it[1] && it[3] !=  it[1] } != null
    }
}
