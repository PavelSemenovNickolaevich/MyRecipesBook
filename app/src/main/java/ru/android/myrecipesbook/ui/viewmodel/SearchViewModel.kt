package ru.android.myrecipesbook.ui.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.android.myrecipesbook.RecipeApiClient
import ru.android.myrecipesbook.db.RecipesDatabase
import ru.android.myrecipesbook.db.entity.DishEntity
import ru.android.myrecipesbook.model.RecipesResponse
import ru.android.myrecipesbook.repository.DishRepository

class SearchViewModel(context: Context) : ViewModel() {

    private val favoriteLiveData = MutableLiveData<RecipesResponse>()
    private val errorLiveData = MutableLiveData<String>()
    private val favStaticLiveDate = MutableLiveData<DishEntity>()
    val db = RecipesDatabase.getDatabase(context)?.dishes()


    val favoriteLiveDataPublicField: LiveData<RecipesResponse> = favoriteLiveData
    val errorLiveDataPublicField: LiveData<String> = errorLiveData
    val liveDataPublicField: LiveData<DishEntity> = favStaticLiveDate

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
        }
    }

     fun saveFavoriteDish(dish: DishEntity){
         viewModelScope.launch {
             db?.saveFavoriteDish(dish)
        }
    }

}


//override fun saveFavoriteDish(dish: DishEntity) {
//    val db = context?.let { DishRepository(it) }
//    db?.saveFavoriteDish(dish)
//}