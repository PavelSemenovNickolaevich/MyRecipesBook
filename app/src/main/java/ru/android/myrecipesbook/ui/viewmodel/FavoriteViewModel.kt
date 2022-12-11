package ru.android.myrecipesbook.ui.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Response
import ru.android.myrecipesbook.RecipeApiClient
import ru.android.myrecipesbook.model.RecipesResponse
import timber.log.Timber

class FavoriteViewModel : ViewModel() {

    val favoriteLiveData = MutableLiveData<RecipesResponse>()
    val errorLiveData = MutableLiveData<String>()

    private val getRecipes = RecipeApiClient.apiClient.getAllRecipes()

    init {
        getRecipeFromNetwork()
    }

    private fun getRecipeFromNetwork() {

        getRecipes.enqueue(object : retrofit2.Callback<List<RecipesResponse>> {

            override fun onResponse(
                call: Call<List<RecipesResponse>>,
                response: Response<List<RecipesResponse>>
            ) {
                favoriteLiveData.value = response.body()?.get(0)
            }

            override fun onFailure(call: Call<List<RecipesResponse>>, t: Throwable) {
                errorLiveData.postValue("Oшибка")
                Timber.tag(ContentValues.TAG).e(t.toString())
            }
        })
    }
}