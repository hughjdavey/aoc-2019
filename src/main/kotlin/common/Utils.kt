package common

import java.util.Stack

fun <T> stackOf(vararg input: T): Stack<T> {
    val stack = Stack<T>()
    input.toList().reversed().forEach { stack.push(it) }
    return stack
}
