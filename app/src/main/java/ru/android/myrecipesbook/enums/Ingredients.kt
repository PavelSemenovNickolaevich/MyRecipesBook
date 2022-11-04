package ru.android.myrecipesbook.enums

import androidx.annotation.StringRes
import ru.android.myrecipesbook.R

enum class Ingredients(@StringRes val ingredientName: Int) {

    SUGAR(R.string.sugar_name),
    BUTTER(R.string.butter_name),
    EGGS(R.string.eggs_name),
    WATER(R.string.water_name),
    STRAWBERRY(R.string.strawberry_name),
    BLUEBERRY(R.string.blueberry_name),
    FISH(R.string.fish_name),
    MEAT(R.string.meat_name),
    SALT(R.string.salt_name),
    FLOUR(R.string.flour_name),
    BREAD(R.string.bread_name),
    CINNAMON(R.string.cinnamon_name)
}