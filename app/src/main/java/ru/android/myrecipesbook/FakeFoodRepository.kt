package ru.android.myrecipesbook

import ru.android.myrecipesbook.enums.FoodCategory
import ru.android.myrecipesbook.enums.Ingredients
import ru.android.myrecipesbook.enums.Meals
import ru.android.myrecipesbook.model.Recipe

class FakeFoodRepository {

    private val frenchToastsWithStrawberry: Recipe = Recipe(
        "Французские тосты с клубникой",
        Meals.BREAKFAST,
        FoodCategory.DESSERT,
        listOf(Ingredients.BUTTER, Ingredients.STRAWBERRY, Ingredients.EGGS, Ingredients.BREAD),
        20,
        300,
        listOf(),
        true,
        4,
        "drawable/french_toast_with_strawsberry.png",
        2
    )

    private val toastsWithCinnamon: Recipe = Recipe(
        "Тосты с корицей",
        Meals.BREAKFAST,
        FoodCategory.DESSERT,
        listOf(
            Ingredients.BUTTER,
            Ingredients.STRAWBERRY,
            Ingredients.EGGS,
            Ingredients.BREAD,
            Ingredients.SUGAR,
            Ingredients.CINNAMON
        ),
        10,
        400,
        listOf(),
        false,
        4,
        "drawable/toast_with_sugar_image.png",
        2
    )

    private val salmonInGlaze: Recipe = Recipe(
        "Лосось в глазури",
        Meals.SUPPER,
        FoodCategory.FISH_DISH,
        listOf(Ingredients.BUTTER, Ingredients.FISH),
        40,
        250,
        listOf(),
        true,
        5,
        "drawable/fish_dish.png",
        1
    )

    private val blueberryMuffins: Recipe = Recipe(
        " Черничные маффины",
        Meals.BREAKFAST,
        FoodCategory.DESSERT,
        listOf(
            Ingredients.BUTTER,
            Ingredients.FISH,
            Ingredients.EGGS,
            Ingredients.FLOUR,
            Ingredients.BLUEBERRY
        ),
        35,
        450,
        listOf(),
        true,
        3,
        "drawable/blueberry_muffins.png",
        4
    )

    private val asianGlazedChickenThighs: Recipe = Recipe(
        "Куриные бедрышки в панировке",
        Meals.DINNER,
        FoodCategory.DESSERT,
        listOf(
            Ingredients.BUTTER,
            Ingredients.MEAT,
            Ingredients.EGGS,
            Ingredients.FLOUR,
            Ingredients.SALT
        ),
        35,
        330,
        listOf(),
        true,
        5,
        "drawable/asian_chicken_glazed_thighs.png",
        2
    )


    fun getListOfDishes(): List<Recipe> {
        return listOf(
            frenchToastsWithStrawberry,
            toastsWithCinnamon,
            salmonInGlaze,
            blueberryMuffins,
            asianGlazedChickenThighs
        )

    }

    fun orderByRates(list: List<Recipe>, isAscendig: Boolean): List<Recipe> {
        return if (isAscendig) list.sortedBy { it.rating } else list.sortedByDescending { it.rating }
    }

    fun getFilterListByMeals(list: List<Recipe>, meals: Meals): List<Recipe> {
        return list.filter { it.meals == meals }
    }

    fun getFilterListByCalories(list: List<Recipe>, calories: Int, isMore: Boolean): List<Recipe> {
        return if (isMore) list.filter { it.calories > calories } else list.filter { it.calories < calories }
    }

    fun getFilterRecipesByTime(list: List<Recipe>, timeMax: Long, timeMin: Long = 0): List<Recipe> {
        return list.filter { it.timeForCooking in timeMin..timeMax }
    }

}

fun main() {
    val test: FakeFoodRepository = FakeFoodRepository()
    println(test.getListOfDishes())
    println(test.orderByRates(test.getListOfDishes(), true))
    println(test.orderByRates(test.getListOfDishes(), false))
    println(test.getFilterListByMeals(test.getListOfDishes(), Meals.SUPPER))
    println(test.getFilterListByCalories(test.getListOfDishes(), 300, true))
    println(test.getFilterRecipesByTime(test.getListOfDishes(), 10))
}