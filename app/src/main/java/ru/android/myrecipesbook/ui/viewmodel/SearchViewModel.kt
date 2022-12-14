package ru.android.myrecipesbook.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.android.myrecipesbook.RecipeApiClient
import ru.android.myrecipesbook.model.RecipesResponse

class SearchViewModel : ViewModel() {

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
                favoriteLiveData.value = getRecipes[0]
            } catch (e: java.lang.Exception) {
                errorLiveData.value = "Error"
            }
        }
    }
}