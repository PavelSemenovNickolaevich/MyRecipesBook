package ru.android.myrecipesbook.model

import androidx.annotation.DrawableRes
import com.google.gson.annotations.SerializedName
import ru.android.myrecipesbook.enums.FoodCategory
import ru.android.myrecipesbook.enums.Ingredients
import ru.android.myrecipesbook.enums.Meals

data class Recipe(
    val name: String?,
    val meals: Meals?,
    @SerializedName("food_category")
    val foodCategory: FoodCategory?,
    val ingredients: List<Ingredients>?,
    @SerializedName("time")
    val timeForCooking: Long?,
    val calories: Int?,
    @SerializedName("steps")
    val stepsOfCooking: List<String>?,
    @SerializedName("favorite")
    val isFavoriteDish: Boolean?,
    @androidx.annotation.IntRange(from = 1, to = 5) val rating: Int?,
    @SerializedName("resource_link_image")
    @DrawableRes val resourceLinkImage: Int?,
    @SerializedName("count_servings")
    val numOfServings: Int?
)