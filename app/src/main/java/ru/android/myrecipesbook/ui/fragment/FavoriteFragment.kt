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
import ru.android.myrecipesbook.adapter.DishAdapterVertical
import ru.android.myrecipesbook.databinding.FragmentFavoriteBinding
import ru.android.myrecipesbook.db.entity.DishEntity
import ru.android.myrecipesbook.repository.DishRepository
import ru.android.myrecipesbook.repository.FakeFoodRepository
import ru.android.myrecipesbook.ui.viewmodel.FavoriteViewModel
import timber.log.Timber


class FavoriteFragment : Fragment(), DishAdapterVertical.Listener {

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


        favoriteViewModel.favoriteLiveDataPublicField.observe(this.viewLifecycleOwner, Observer {

            recycleViewDish.adapter = favoriteViewModel.favoriteLiveDataPublicField.value?.let {
                DishAdapterVertical(
                    it,
                    R.layout.list_item_vertical_dish,
                    this
                )
            }
        }
        )

        favoriteViewModel.errorLiveDataPublicField.observe(
            this.viewLifecycleOwner,
            Observer<String> { error ->
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
            })

        return root
    }

    override fun onClickFavoriteDishCheckBox(like: CheckBox, dishName: String, dish: DishEntity) {

//        if (like.isChecked) {
//            searhViewModelVertical.saveFavoriteDish(dish)
//            Toast.makeText(
//                context,
//                "\"$dishName\" был сохранен в любимых рецептах",
//                Toast.LENGTH_LONG
//            ).show()
//        } else {
//            searhViewModelVertical.deleteFavoriteDish(dishName)
//            Toast.makeText(
//                context,
//                "\"$dishName\" был удален из любимых рецептов",
//                Toast.LENGTH_LONG
//            ).show()
//        }
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
