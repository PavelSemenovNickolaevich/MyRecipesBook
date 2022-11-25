package ru.android.myrecipesbook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.android.myrecipesbook.databinding.FragmentFavoriteBinding
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
        val dish = fakeFoodRepository.getListOfDishes()
        recycleViewDish.adapter = DishAdapter(dish, R.layout.list_item_vertical_dish)

        return root
    }
}