package ru.android.myrecipesbook

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.android.myrecipesbook.`interface`.RecipeApiInterface

object RecipeApiClient {

    private var BASE_URL = "https://61e46d241a976f00176ee4a1.mockapi.io/api/v1/"


    private var client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }).build()

    val apiClient: RecipeApiInterface by lazy {

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return@lazy retrofit.create(RecipeApiInterface::class.java)
    }
}