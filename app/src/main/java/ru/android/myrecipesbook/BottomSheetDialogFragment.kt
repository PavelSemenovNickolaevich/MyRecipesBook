package ru.android.myrecipesbook

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.google.android.material.chip.ChipGroup
import ru.android.myrecipesbook.databinding.FragmentBottomSheetDialogBinding
import ru.android.myrecipesbook.enums.FoodCategory
import ru.android.myrecipesbook.enums.Meals
import java.io.Serializable


private const val FILTER_CHANGED = "filterChanged"
private const val BUNDLE_KEY = "bundleKey"

class BottomSheetDialogFragment :
    com.google.android.material.bottomsheet.BottomSheetDialogFragment() {

    lateinit var binding: FragmentBottomSheetDialogBinding

    companion object {
        fun newInstance(): BottomSheetDialogFragment {
            return BottomSheetDialogFragment()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomSheetDialogBinding.inflate(inflater, container, false)
        val root = binding.root
        Log.d("This is log for on Create", "CREEEEEEATTEED")

        val countOfServingSeekBar: SeekBar = binding.countOfServingSeekbar
        val countOfServingValue: TextView = binding.countOfServingSeekbarValue
        val countOfCaloriesSeekBar: SeekBar = binding.caloriesSeekbar
        val countOfCaloriesValue: TextView = binding.caloriesSeekbarValue
        val timeToCookSeekbar: SeekBar = binding.timeToCookSeekbar
        val timeToCookValue: TextView = binding.timeToCookSeekbarValue
        val ratingSeekBar: SeekBar = binding.ratingSeekbar
        val ratingValue: TextView = binding.ratingSeekbarValue
        val applyBtn: Button = binding.applyBtn
        val chipGroupOfMeals: ChipGroup = binding.chipGroupOfMeals
        val chipGroupOfDish: ChipGroup = binding.chipGroupOfDish

        val data = SelectedFilterHolder()

        chipGroupOfMeals.setOnCheckedStateChangeListener { group, checkedId ->

            data.selectedMeals =
                when (checkedId.first()) {
                    R.id.chip_breakfast -> Meals.BREAKFAST.toString()
                    R.id.chip_late_breakfast -> Meals.LATE_BREAKFAST.toString()
                    R.id.chip_supper -> Meals.SUPPER.toString()
                    R.id.chip_dinner -> Meals.DINNER.toString()
                    else -> {
                        ""
                    }
                }
            setFragmentResult(FILTER_CHANGED, bundleOf(BUNDLE_KEY to data))
        }

        chipGroupOfDish.setOnCheckedStateChangeListener { group, checkedId ->

            data.selectedDish =
                when (checkedId.first()) {
                    R.id.chip_soups -> FoodCategory.SOUP.toString()
                    R.id.chip_dessert -> FoodCategory.DESSERT.toString()
                    R.id.chip_drinks -> FoodCategory.DRINK.toString()
                    R.id.chip_snacks -> FoodCategory.SNACK.toString()
                    R.id.chip_main_dish -> FoodCategory.MAIN_COURSE.toString()
                    R.id.chip_first_dish -> FoodCategory.FIRST_COURSE.toString()
                    R.id.chip_meat_dish -> FoodCategory.MEAT_DISH.toString()
                    else -> {
                        ""
                    }
                }
            setFragmentResult(FILTER_CHANGED, bundleOf(BUNDLE_KEY to data))
        }

        data.selectedCountOfServings = setSeekBarValue(countOfServingSeekBar, countOfServingValue)
        data.selectedCountOfCalories = setSeekBarValue(countOfCaloriesSeekBar, countOfCaloriesValue)
        data.selectedRating = setSeekBarValue(timeToCookSeekbar, timeToCookValue).toFloat()
        data.selectedTimeToCook = setSeekBarValue(timeToCookSeekbar, timeToCookValue)

        applyBtn.setOnClickListener {
            this.dismiss()
        }

        return root
    }

    private fun setSeekBarValue(
        seekBar: SeekBar,
        value: TextView,
    ): Int {
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {
                value.text = progress.toString()

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                //                TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                //                TODO("Not yet implemented")
            }
        })
        return value.text.toString().toInt()
    }


    class SelectedFilterHolder(

        var selectedMeals: String? = null,
        var selectedDish: String? = null,
        var selectedCountOfServings: Int = 0,
        var selectedTimeToCook: Int = 0,
        var selectedCountOfCalories: Int = 0,
        var selectedRating: Float = 0f

    ) : Serializable

}

