package io.github.ComposeKit2.Model

data class Borrowings(
    val id: Int,
    val borrowerEmail: String,
    val borrowDate: String,
    val returnDate: String,
    val status: String,
    val namaItem: String,
    val namaCategory: String
)

data class BorrowRequest(
    val itemId: Int,
    val email: String
)

data class ReturnRequest(
    val id: Int
)

data class ReturnResponse(
    val message: String
)
