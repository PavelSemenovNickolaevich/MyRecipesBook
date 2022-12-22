package ru.android.myrecipesbook.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import ru.android.myrecipesbook.R
import ru.android.myrecipesbook.adapter.DishAdapterHorizontal
import ru.android.myrecipesbook.adapter.DishAdapterVertical
import ru.android.myrecipesbook.databinding.FragmentSearchBinding
import ru.android.myrecipesbook.db.entity.DishEntity
import ru.android.myrecipesbook.repository.DishRepository
import ru.android.myrecipesbook.repository.FakeFoodRepository
import ru.android.myrecipesbook.ui.viewmodel.SearchViewModel
import timber.log.Timber

class SearchFragment : Fragment(), DishAdapterHorizontal.Listener, DishAdapterVertical.Listener {

    private lateinit var binding: FragmentSearchBinding
    private val fakeFoodRepository = FakeFoodRepository
    private lateinit var searhViewModelVertical: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        searhViewModelVertical = ViewModelProvider(this)[SearchViewModel::class.java]
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root = binding.root
        val filtersBtn: ImageButton = binding.filterBtn
//        val fullRecipeButton: ImageView = binding.recipe1
        Timber.d("This is log for on Create SearchFragment")

        val recycleViewDishVertical = binding.recycleDishVertical
        recycleViewDishVertical.layoutManager = LinearLayoutManager(activity)

        val recycleViewDishHorizontal = binding.recycleDishHorizontal
        recycleViewDishHorizontal.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val dish = fakeFoodRepository.getListOfDishes()
        recycleViewDishHorizontal.adapter =
            DishAdapterHorizontal(dish, R.layout.list_item_horizontal_dish, this)

        val dishFromDb = context?.let { DishRepository(it) }?.getFavoriteDishByLike()
//        if (dishFromDb != null) {
//            recycleViewDishHorizontal.adapter =
//                DishAdapterHorizontal(dishFromDb, R.layout.list_item_vertical_dish, this)
//        }

        Timber.d("This is log for on Create FavoriteFragment")

        val recycleViewDish = binding.recycleDishVertical
        recycleViewDish.layoutManager = LinearLayoutManager(activity)


        searhViewModelVertical.favoriteLiveDataPublicField.observe(
            this.viewLifecycleOwner,
            Observer {

                recycleViewDish.adapter =
                    searhViewModelVertical.favoriteLiveDataPublicField.value?.let {
                        DishAdapterVertical(
                            it,
                            R.layout.list_item_vertical_dish,
                            this
                        )
                    }
            }
        )

        searhViewModelVertical.errorLiveDataPublicField.observe(
            this.viewLifecycleOwner,
            Observer<String> { error ->
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
            })

        //Оставил специально
//        val getRecipes = RecipeApiClient.apiClient.getAllRecipes()
//
//
//        getRecipes.enqueue(object : retrofit2.Callback<List<RecipesResponse>> {
//
//            override fun onResponse(
//                call: Call<List<RecipesResponse>>,
//                response: Response<List<RecipesResponse>>
//            ) {
//                val dish = response.body()?.get(0)
//                recycleViewDishVertical.adapter =
//                    dish?.let { DishAdapterVertical(it, R.layout.list_item_vertical_dish) }
//                Log.d("", response.toString())
//            }
//
//            override fun onFailure(call: Call<List<RecipesResponse>>, t: Throwable) {
//                Log.e(ContentValues.TAG, t.toString())
//            }
//
//        })

        filtersBtn.setOnClickListener {
            val addBottomFragment: BottomSheetDialogFragment =
                BottomSheetDialogFragment.newInstance()
            addBottomFragment.show(
                childFragmentManager,
                "add_dialog"
            )
        }

        childFragmentManager.setFragmentResultListener(
            "filterChanged",
            viewLifecycleOwner
        ) { key, bundle ->
            val result: BottomSheetDialogFragment.SelectedFilterHolder =
                bundle.getSerializable("bundleKey") as BottomSheetDialogFragment.SelectedFilterHolder

            Log.d("FragmentResultListener", result.selectedMeals.toString())
        }

//        fullRecipeButton.setOnClickListener {
//            val intent = Intent(context, ReceipeDetailsActivity::class.java)
//            startActivity(intent)
//        }


        return root
    }

    override fun onClickFavoriteDishCheckBox(like: CheckBox, dishName: String) {
        if (like.isChecked) {
            Toast.makeText(
                context,
                "\"$dishName\" был сохранен в любимых рецептах",
                Toast.LENGTH_LONG
            ).show()
        } else {
            Toast.makeText(
                context,
                "\"$dishName\" был удален из любимых рецептов",
                Toast.LENGTH_LONG
            ).show()
        }
    }



     override suspend fun saveFavoriteDish(dish: DishEntity){
         val db = context?.let { DishRepository(it) }
        db?.saveFavoriteDish(dish)
    }

    override fun deleteFavoriteDish(dishName: String) {
        val db = context?.let { DishRepository(it) }
        db?.deleteFavoriteDish(dishName)
    }

//    fun save(isChecked: Boolean, key: String?) {
//        val sharedPreferences = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
//        val editor = sharedPreferences.edit()
//        editor.putBoolean(key, isChecked)
//        editor.apply()
//    }
}
