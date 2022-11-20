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
                when (checkedId) {
                    checkedId.map { R.id.chip_breakfast.toString() } -> Meals.BREAKFAST.toString()
                    checkedId.map { R.id.chip_late_breakfast.toString() } -> Meals.LATE_BREAKFAST.toString()
                    checkedId.map { R.id.chip_supper.toString() } -> Meals.SUPPER.toString()
                    checkedId.map { R.id.chip_dinner.toString() } -> Meals.DINNER.toString()
                    else -> {
                        ""
                    }
                }
            setFragmentResult("filterChanged", bundleOf("bundleKey" to data))
        }

        chipGroupOfDish.setOnCheckedStateChangeListener { group, checkedId ->

            data.selectedDish =
                when (checkedId) {
                    checkedId.map { R.id.chip_soups.toString() } -> FoodCategory.SOUP.toString()
                    checkedId.map { R.id.chip_dessert.toString() } -> FoodCategory.DESSERT.toString()
                    checkedId.map { R.id.chip_drinks.toString() } -> FoodCategory.DRINK.toString()
                    checkedId.map { R.id.chip_snacks.toString() } -> FoodCategory.SNACK.toString()
                    checkedId.map { R.id.chip_main_dish.toString() } -> FoodCategory.MAIN_COURSE.toString()
                    checkedId.map { R.id.chip_first_dish.toString() } -> FoodCategory.FIRST_COURSE.toString()
                    checkedId.map { R.id.chip_meat_dish.toString() } -> FoodCategory.MEAT_DISH.toString()
                    else -> {
                        ""
                    }
                }
            setFragmentResult("filterChanged", bundleOf("bundleKey" to data))
        }

        data.selectedCountOfServings = setSeekBarValue(countOfServingSeekBar, countOfServingValue)
        data.selectedCountOfCalories = setSeekBarValue(countOfCaloriesSeekBar, countOfCaloriesValue)
        data.selectedRating = setSeekBarValue(timeToCookSeekbar, timeToCookValue).toFloat()
        data.selectedTimeToCook = setSeekBarValue(timeToCookSeekbar, timeToCookValue)
        setSeekBarValue(ratingSeekBar, ratingValue)

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

