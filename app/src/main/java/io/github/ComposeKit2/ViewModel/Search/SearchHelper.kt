package io.github.ComposeKit2.ViewModel.Search

object SearchHelper {
    fun <T> filter(
        items: List<T>,
        query: String,
        selector: (T) -> String
    ): List<T> {
        if (query.isBlank()) return items

        return items.filter {
            selector(it).contains(query, ignoreCase = true)
        }
    }
}

object FilterHelper {
    fun <T, V> filter(
        items: List<T>,
        selected: V?,
        filterBy: (T) -> V
    ): List<T> {
        return items.filter {
            selected == null || filterBy(it) == selected
        }
    }
}