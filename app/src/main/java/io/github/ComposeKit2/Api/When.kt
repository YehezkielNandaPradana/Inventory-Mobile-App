package io.github.ComposeKit2.Api

inline fun <T> List<T>.whereIf(
    condition: Boolean,
    predicate: (T) -> Boolean
): List<T> {
    return if (condition) filter(predicate) else this
}