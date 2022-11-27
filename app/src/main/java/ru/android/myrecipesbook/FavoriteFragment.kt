package ru.android.myrecipesbook

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Response
import ru.android.myrecipesbook.databinding.FragmentFavoriteBinding
import ru.android.myrecipesbook.model.RecipesResponse
import timber.log.Timber


class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private var fakeFoodRepository = FakeFoodRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val root = binding.root

        Timber.d("This is log for on Create FavoriteFragment")

        val recycleViewDish = binding.recycleDishVertical
        recycleViewDish.layoutManager = LinearLayoutManager(activity)
//        val dish = fakeFoodRepository.getListOfDishes()
//        recycleViewDish.adapter = DishAdapter(dish, R.layout.list_item_vertical_dish)

        val getRecipes = RecipeApiClient.apiClient.getAllRecipes()

        getRecipes.enqueue(object : retrofit2.Callback<List<RecipesResponse>> {

            override fun onResponse(
                call: Call<List<RecipesResponse>>,
                response: Response<List<RecipesResponse>>
            ) {
                val dish = response.body()!![0]
                recycleViewDish.adapter = DishAdapterVertical(dish, R.layout.list_item_vertical_dish)
                Log.d("",response.toString())
            }

            override fun onFailure(call: Call<List<RecipesResponse>>, t: Throwable) {
               Log.e(TAG, t.toString())
            }

        })

        return root
    }
}