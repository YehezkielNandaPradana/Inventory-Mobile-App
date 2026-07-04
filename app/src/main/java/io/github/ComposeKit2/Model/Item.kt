package io.github.ComposeKit2.Model

data class Items(
    val id: Int,
    val name: String,
    val totalStock: Int,
    val availableStock: Int,
    val categoryId: Int,
    val categoryName: String,
)

data class AddItemRequest(
    val idCategory: Int,
    val name: String,
    val totalStock: Int
)

data class AddItemResponse(
    val message : String
)