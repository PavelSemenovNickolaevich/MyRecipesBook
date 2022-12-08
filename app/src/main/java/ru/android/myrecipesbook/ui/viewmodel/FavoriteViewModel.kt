package ru.android.myrecipesbook.ui.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Response
import ru.android.myrecipesbook.R
import ru.android.myrecipesbook.RecipeApiClient
import ru.android.myrecipesbook.adapter.DishAdapterVertical
import ru.android.myrecipesbook.model.RecipesResponse

class FavoriteViewModel : ViewModel() {

    val favoriteLiveData = MutableLiveData<RecipesResponse>()
    val errorLiveData = MutableLiveData<String>()


    private val getRecipes = RecipeApiClient.apiClient.getAllRecipes()

    fun getRecipeFromNetwork(recycleViewDish: RecyclerView) {

        getRecipes.enqueue(object : retrofit2.Callback<List<RecipesResponse>> {

            override fun onResponse(
                call: Call<List<RecipesResponse>>,
                response: Response<List<RecipesResponse>>
            ) {
                favoriteLiveData.value = response.body()?.get(0)
                println(favoriteLiveData.value)
                recycleViewDish.adapter = favoriteLiveData.value?.let {
                    DishAdapterVertical(
                        it,
                        R.layout.list_item_vertical_dish
                    )
                }
                Log.d("", response.toString())
            }

            override fun onFailure(call: Call<List<RecipesResponse>>, t: Throwable) {
                errorLiveData.postValue("Oшибка")
                Log.e(ContentValues.TAG, t.toString())
            }
        })
    }

}