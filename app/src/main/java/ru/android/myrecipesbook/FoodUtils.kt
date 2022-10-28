package ru.android.myrecipesbook

import androidx.annotation.DrawableRes
import ru.android.myrecipesbook.enums.Ingredients

class FoodUtils {

    @DrawableRes
    fun resolveFoodIngredient(ingredients: Ingredients): Int {

        @DrawableRes
        val id: Int = when (ingredients) {

            Ingredients.SUGAR -> R.drawable.sugar_image
            Ingredients.EGGS -> R.drawable.ingridient_eggs
            Ingredients.STRAWBERRY -> R.drawable.strawberry_image
            Ingredients.WATER -> R.drawable.water_image
            Ingredients.BUTTER -> R.drawable.ingridients_butter
            Ingredients.BLUEBERRY -> R.drawable.berries_image

            else -> {
                throw Exception("Ingredient does not exist")
            }
        }
        return id
    }
}