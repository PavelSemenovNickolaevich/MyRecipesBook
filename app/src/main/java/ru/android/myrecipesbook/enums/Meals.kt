package ru.android.myrecipesbook.enums

import androidx.annotation.StringDef
import androidx.annotation.StringRes
import com.google.gson.annotations.SerializedName
import ru.android.myrecipesbook.R

enum class Meals(@StringRes val typeOfMeals: Int) {

    @SerializedName("breakfast")
    BREAKFAST(R.string.breakfast_txt),
    @SerializedName("dinner")
    DINNER(R.string.dinner_txt),
    @SerializedName("supper")
    SUPPER(R.string.supper_txt),
    @SerializedName("late_breakfast")
    LATE_BREAKFAST(R.string.late_breakfast_txt)
}