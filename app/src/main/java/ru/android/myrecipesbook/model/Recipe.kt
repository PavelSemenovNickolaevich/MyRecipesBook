package ru.android.myrecipesbook.model

import ru.android.myrecipesbook.enums.FoodCategory
import ru.android.myrecipesbook.enums.Ingredients
import ru.android.myrecipesbook.enums.Meals

data class Recipe(
    val name: String,
    val meals: Meals,
    val foodCategory: FoodCategory,
    val ingredients: List<Ingredients>,
    val timeForCooking: Long,
    val calories: Int,
    val stepsOfCooking: List<String>,
    val isFavoriteDish: Boolean,
    @androidx.annotation.IntRange(from = 1, to = 5) val rating: Int,
    val resourceLinkImage: String,
    val numOfServings: Int
) {


}