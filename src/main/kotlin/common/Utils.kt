package common

import java.util.Stack

fun <T> stackOf(vararg input: T): Stack<T> {
    val stack = Stack<T>()
    input.toList().reversed().forEach { stack.push(it) }
    return stack
}

// credit LukArToDo - https://code.sololearn.com/c24EP02YuQx3/#kt
fun <T> permutations(ts: List<T>): List<List<T>> {
    if (ts.size == 1) {
        return listOf(ts)
    }
    val perms = mutableListOf<List<T>>()
    val sub = ts[0]
    for (perm in permutations(ts.drop(1))) {
        for (i in 0..perm.size) {
            val newPerm = perm.toMutableList()
            newPerm.add(i, sub)
            perms.add(newPerm)
        }
    }
    return perms
}

class CircularList<T>(vararg val elements: T) {

    private val size: Int
    private var head = -1

    init {
        if (elements.isEmpty()) {
            throw IllegalArgumentException("Array size must be greater than 0")
        }
        this.size = elements.size
    }

    fun next(): T {
        if (++head == size) {
            head = 0
        }
        return elements[head]
    }

    fun prev(): T {
        if (--head < 0) {
            head = size - 1
        }
        return elements[head]
    }
}

// taken from https://youtrack.jetbrains.com/issue/KT-7657
fun <R, T> Sequence<T>.scan(seed: R, transform: (a: R, b: T) -> R): Sequence<R> = object : Sequence<R> {
    override fun iterator(): Iterator<R> = object : Iterator<R> {
        val it = this@scan.iterator()
        var last: R = seed
        var first = true

        override fun next(): R {
            if (first) first = false else last = transform(last, it.next())
            return last
        }

        override fun hasNext(): Boolean = it.hasNext()
    }
}
