package ru.android.myrecipesbook

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Response
import ru.android.myrecipesbook.databinding.FragmentSearchBinding
import ru.android.myrecipesbook.model.RecipesResponse
import timber.log.Timber

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val fakeFoodRepository = FakeFoodRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
            DishAdapterHorizontal(dish, R.layout.list_item_horizontal_dish)


        val getRecipes = RecipeApiClient.apiClient.getAllRecipes()

        getRecipes.enqueue(object : retrofit2.Callback<List<RecipesResponse>> {

            override fun onResponse(
                call: Call<List<RecipesResponse>>,
                response: Response<List<RecipesResponse>>
            ) {
                val dish = response.body()!![0]
                recycleViewDishVertical.adapter =
                    DishAdapterVertical(dish, R.layout.list_item_vertical_dish)
                Log.d("", response.toString())
            }

            override fun onFailure(call: Call<List<RecipesResponse>>, t: Throwable) {
                Log.e(ContentValues.TAG, t.toString())
            }

        })

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
}
