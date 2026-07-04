package ComposeKit.Helper.Api

import io.github.ComposeKit2.Model.AddItemRequest
import io.github.ComposeKit2.Model.AddItemResponse
import io.github.ComposeKit2.Model.BorrowRequest
import io.github.ComposeKit2.Model.Borrowings
import io.github.ComposeKit2.Model.Categories
import io.github.ComposeKit2.Model.Items
import io.github.ComposeKit2.Model.ReturnResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("api/Item")
    suspend fun getItem(): List<Items>

    @GET("api/Categories")
    suspend fun getCategories(): List<Categories>

    @POST("api/borrowings/borrow")
    suspend fun postBorrow(
        @Body request: BorrowRequest,
    ): Borrowings

    @POST("api/Item/AddItem")
    suspend fun addItem(
        @Body request: AddItemRequest,
    ): AddItemResponse

    @GET("api/borrowings")
    suspend fun getBorrowings(): List<Borrowings>

    @PUT("api/borrowings/return/{id}")
    suspend fun returnItem(
       @Path("id") id: Int
    ):ReturnResponse
}