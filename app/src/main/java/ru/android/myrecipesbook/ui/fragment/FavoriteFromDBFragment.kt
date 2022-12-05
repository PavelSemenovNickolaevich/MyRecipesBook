package ru.android.myrecipesbook.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.android.myrecipesbook.R
import ru.android.myrecipesbook.adapter.DishAdapterEntityVertical
import ru.android.myrecipesbook.databinding.FragmentFavoriteFromDBBinding
import ru.android.myrecipesbook.db.entity.DishEntity
import ru.android.myrecipesbook.repository.DishRepository
import timber.log.Timber


class FavoriteFromDBFragment : Fragment(), DishAdapterEntityVertical.Listener {

    private lateinit var binding: FragmentFavoriteFromDBBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteFromDBBinding.inflate(inflater, container, false)
        val root = binding.root

        Timber.d("This is log for on Create ProfileFragment")

        val dishFromDb = context?.let { DishRepository(it) }?.getFavoriteDish()
        val recycleViewDish = binding.recycleDishVertical
        recycleViewDish.layoutManager = LinearLayoutManager(activity)
        if (dishFromDb != null) {
            recycleViewDish.adapter =
                DishAdapterEntityVertical(dishFromDb, R.layout.list_item_vertical_dish, this)
        }
        return root
    }

    override fun onClickFavoriteDishCheckBox(like: CheckBox, dishName: String) {
        if (!like.isChecked) {
            Toast.makeText(
                context,
                "\"$dishName\" был удален из любимых рецептов",
                Toast.LENGTH_LONG
            ).show()
        } else {
            Toast.makeText(
                context,
                "\"$dishName\" был добавлен снова",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun saveFavoriteDish(dish: DishEntity) {
        val db = context?.let { DishRepository(it) }
        db?.saveFavoriteDish(dish)
    }

    override fun deleteFavoriteDish(dishName: String) {
        val db = context?.let { DishRepository(it) }
        db?.deleteFavoriteDish(dishName)
    }
}