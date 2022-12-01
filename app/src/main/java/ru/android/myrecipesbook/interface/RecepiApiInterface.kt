package ru.android.myrecipesbook.`interface`

import retrofit2.Call
import retrofit2.http.GET
import ru.android.myrecipesbook.model.RecipesResponse

interface RecipeApiInterface {

    @GET("recipes")
    fun getAllRecipes(): Call<List<RecipesResponse>>
}