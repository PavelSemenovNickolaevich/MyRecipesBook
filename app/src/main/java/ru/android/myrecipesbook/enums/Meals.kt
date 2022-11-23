package ru.android.myrecipesbook.enums

import androidx.annotation.StringDef
import androidx.annotation.StringRes
import ru.android.myrecipesbook.R

enum class Meals(@StringRes val typeOfMeals: Int) {

    BREAKFAST(R.string.breakfast_txt),
    DINNER(R.string.dinner_txt),
    SUPPER(R.string.supper_txt),
    LATE_BREAKFAST(R.string.late_breakfast_txt)

}