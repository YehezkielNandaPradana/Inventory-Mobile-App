package ComposeKit.Helper.Api

import okhttp3.OkHttpClient
import okhttp3.Protocol
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val service: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5248/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}