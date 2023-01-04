package ru.android.myrecipesbook.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.android.myrecipesbook.RecipeApiClient
import ru.android.myrecipesbook.model.RecipesResponse

class FavoriteViewModel : ViewModel() {

    private val favoriteLiveData = MutableLiveData<RecipesResponse>()
    private val errorLiveData = MutableLiveData<String>()

    val favoriteLiveDataPublicField: LiveData<RecipesResponse> = favoriteLiveData
    val errorLiveDataPublicField: LiveData<String> = errorLiveData

    init {
        getRecipeFromNetwork()
    }


    private fun getRecipeFromNetwork() {

        viewModelScope.launch {

            try {
                val getRecipes = RecipeApiClient.apiClient.getAllRecipes()
                if(getRecipes.isNullOrEmpty()) throw Exception("List is Empty")
                favoriteLiveData.value = getRecipes[0]
            } catch (e: java.lang.Exception) {
                errorLiveData.value = "Error"
            }


            //Оставил специально
//            val getRecipes = RecipeApiClient.apiClient.getAllRecipes()

//            getRecipes.enqueue(object : retrofit2.Callback<List<RecipesResponse>> {

//                override fun onResponse(
//                    call: Call<List<RecipesResponse>>,
//                    response: Response<List<RecipesResponse>>
//                ) {
//                    favoriteLiveData.value = response.body()?.get(0)
            //   favoriteLiveData.value = getRecipes[0]
//            favoriteLiveData.value = getRecipes
//
//                }

//                override fun onFailure(call: Call<List<RecipesResponse>>, t: Throwable) {
//                    errorLiveData.postValue("Oшибка")
//                    Timber.tag(ContentValues.TAG).e(t.toString())
//                }
//            })
        }
    }
}