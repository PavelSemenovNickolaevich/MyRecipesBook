package ru.android.myrecipesbook.ui.fragment

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Response
import ru.android.myrecipesbook.R
import ru.android.myrecipesbook.RecipeApiClient
import ru.android.myrecipesbook.adapter.DishAdapterVertical
import ru.android.myrecipesbook.databinding.FragmentFavoriteBinding
import ru.android.myrecipesbook.model.RecipesResponse
import ru.android.myrecipesbook.repository.FakeFoodRepository
import ru.android.myrecipesbook.ui.viewmodel.FavoriteViewModel
import timber.log.Timber


class FavoriteFragment : Fragment() {

    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var binding: FragmentFavoriteBinding
    private var fakeFoodRepository = FakeFoodRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        favoriteViewModel = ViewModelProvider(this)[FavoriteViewModel::class.java]
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val root = binding.root

        Timber.d("This is log for on Create FavoriteFragment")

        val recycleViewDish = binding.recycleDishVertical
        recycleViewDish.layoutManager = LinearLayoutManager(activity)

        favoriteViewModel.favoriteLiveData.observe(this.viewLifecycleOwner, Observer {
            DishAdapterVertical(it, R.layout.list_item_vertical_dish)
        }
        )
        favoriteViewModel.errorLiveData.observe(this.viewLifecycleOwner, Observer<String> { error ->
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        })

        favoriteViewModel.getRecipeFromNetwork(recycleViewDish)

        return root
    }
}
