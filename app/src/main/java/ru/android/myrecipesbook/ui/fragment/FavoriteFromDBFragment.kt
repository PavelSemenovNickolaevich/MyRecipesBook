package ru.android.myrecipesbook.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.android.myrecipesbook.R
import ru.android.myrecipesbook.adapter.DishAdapterEntityVertical
import ru.android.myrecipesbook.databinding.FragmentFavoriteFromDBBinding
import ru.android.myrecipesbook.db.entity.DishEntity
import ru.android.myrecipesbook.repository.DishRepository
import ru.android.myrecipesbook.ui.viewmodel.FavoriteViewModelDB
import ru.android.myrecipesbook.utils.FavoriteFromDBViewModelFactory
import timber.log.Timber


class FavoriteFromDBFragment : Fragment(), DishAdapterEntityVertical.Listener {

    private lateinit var binding: FragmentFavoriteFromDBBinding
    private lateinit var favoriteViewModelDB: FavoriteViewModelDB


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModelFactory = FavoriteFromDBViewModelFactory(
            requireActivity().application
        )
        favoriteViewModelDB =
            ViewModelProvider(this, viewModelFactory).get(FavoriteViewModelDB::class.java)
        binding = FragmentFavoriteFromDBBinding.inflate(inflater, container, false)
        val root = binding.root

        Timber.d("This is log for on Create ProfileFragment")

        favoriteViewModelDB.getFavoriteDishFromDBByLike()

        val recycleViewDish = binding.recycleDishVertical
        recycleViewDish.layoutManager = LinearLayoutManager(activity)

        favoriteViewModelDB.favoriteLiveDataPublicFieldByLike.observe(
            this.viewLifecycleOwner,
            Observer {
                recycleViewDish.adapter =
                    it?.let { it1 ->
                        DishAdapterEntityVertical(
                            it1,
                            R.layout.list_item_vertical_dish,
                            this
                        )
                    }
            })
        return root
    }

    override fun onClickFavoriteDishCheckBox(like: CheckBox, dishName: String, dish: DishEntity) {
        if (!like.isChecked) {
            favoriteViewModelDB.deleteFavoriteDish(dishName)
            Toast.makeText(
                context,
                "\"$dishName\" был удален из любимых рецептов",
                Toast.LENGTH_LONG
            ).show()
        } else {
            favoriteViewModelDB.saveFavoriteDish(dish)
            Toast.makeText(
                context,
                "\"$dishName\" был добавлен снова",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override suspend fun saveFavoriteDish(dish: DishEntity) {
        val db = context?.let { DishRepository(it) }
        db?.saveFavoriteDish(dish)
    }

    override suspend fun deleteFavoriteDish(dishName: String) {
        val db = context?.let { DishRepository(it) }
        db?.deleteFavoriteDish(dishName)
    }
}