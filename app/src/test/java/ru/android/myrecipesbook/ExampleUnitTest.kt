package ru.android.myrecipesbook

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.android.myrecipesbook.FakeFoodRepository.getFilterListByCalories
import ru.android.myrecipesbook.FakeFoodRepository.getFilterListByMeals
import ru.android.myrecipesbook.FakeFoodRepository.getFilterRecipesByTime
import ru.android.myrecipesbook.FakeFoodRepository.getListOfDishes
import ru.android.myrecipesbook.FakeFoodRepository.orderByRates
import ru.android.myrecipesbook.enums.FoodCategory
import ru.android.myrecipesbook.enums.Ingredients
import ru.android.myrecipesbook.enums.Meals
import ru.android.myrecipesbook.model.Recipe

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    companion object {

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
            R.drawable.french_toast_with_strawsberry,
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
            R.drawable.toast_with_sugar_image,
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
            R.drawable.fish_dish,
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
            R.drawable.blueberry_muffins,
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
            R.drawable.asian_chicken_glazed_thighs,
            2
        )

        val actualOrderByRatesAsc: List<Recipe> = listOf(
            blueberryMuffins,
            frenchToastsWithStrawberry,
            toastsWithCinnamon,
            salmonInGlaze,
            asianGlazedChickenThighs
        )

        val actualOrderByRatesDesc: List<Recipe> = listOf(
            salmonInGlaze,
            asianGlazedChickenThighs,
            frenchToastsWithStrawberry,
            toastsWithCinnamon,
            blueberryMuffins,
        )

        val actualFilterByMeals: List<Recipe> = listOf(
            salmonInGlaze,
        )

        val actualFilterByCalories: List<Recipe> = listOf(
            toastsWithCinnamon,
            blueberryMuffins,
            asianGlazedChickenThighs
        )

        val actualFilterByTime: List<Recipe> = listOf(
            blueberryMuffins,
            asianGlazedChickenThighs
        )

        val path: FoodUtils = FoodUtils()
    }

    @Test
    fun checkOrderByRatesAsc() {
        assertEquals(
            orderByRates(getListOfDishes(), true), actualOrderByRatesAsc
        )
    }

    @Test
    fun checkOrderByRatesDesc() {
        assertEquals(
            orderByRates(getListOfDishes(), false), actualOrderByRatesDesc
        )
    }

    @Test
    fun checkFilterByMeals() {
        assertEquals(
            getFilterListByMeals(getListOfDishes(), Meals.SUPPER), actualFilterByMeals
        )
    }

    @Test
    fun checkFilterByCalories() {
        assertEquals(
            getFilterListByCalories(getListOfDishes(), 300, true), actualFilterByCalories
        )
    }

    @Test
    fun checkFilterByTime() {
        assertEquals(
            getFilterRecipesByTime(getListOfDishes(), 35, 35), actualFilterByTime
        )
    }

    @Test
    fun checkIdDrawable() {
        assertEquals(path.resolveFoodIngredient(Ingredients.SUGAR), 2131165414)
    }
}