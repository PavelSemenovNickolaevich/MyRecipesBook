package ru.android.myrecipesbook

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.Fragment
import ru.android.myrecipesbook.databinding.FragmentSearchBinding
import timber.log.Timber

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private var fakeFoodRepository = FakeFoodRepository


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root = binding.root
        val filtersBtn: ImageButton = binding.filterBtn
        val fullRecipeButton: ImageView = binding.recipe1
        Timber.d("This is log for on Create")

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

        fullRecipeButton.setOnClickListener {
            val intent = Intent(context, ReceipeDetailsActivity::class.java)
            startActivity(intent)
        }
        return root
    }
}
