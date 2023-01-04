package ru.android.myrecipesbook.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.android.myrecipesbook.db.RecipesDatabase
import ru.android.myrecipesbook.db.entity.DishEntity

class FavoriteViewModelDB(application: Application) : ViewModel() {

    private val favoriteLiveData = MutableLiveData<List<DishEntity>?>()
    private val favoriteLiveDataByLike = MutableLiveData<List<DishEntity>?>()
    val db = RecipesDatabase.getDatabase(application)?.dishes()

    val favoriteLiveDataPublicField: LiveData<List<DishEntity>?> = favoriteLiveData
    val favoriteLiveDataPublicFieldByLike: LiveData<List<DishEntity>?> = favoriteLiveDataByLike

    fun getFavoriteDishFromDB() {
        viewModelScope.launch {
            val recipes = db?.getFavoriteDish()
            favoriteLiveData.postValue(recipes)
        }
    }

    fun getFavoriteDishFromDBByLike() {
        viewModelScope.launch {
            val recipes = db?.getFavoriteDishByLike()
            favoriteLiveDataByLike.postValue(recipes)
        }
    }

    fun saveFavoriteDish(dish: DishEntity) {
        viewModelScope.launch {
            db?.saveFavoriteDish(dish)
        }
    }

    fun deleteFavoriteDish(dishName: String) {
        viewModelScope.launch {
            db?.deleteFavoriteDish(dishName)
        }
    }

}