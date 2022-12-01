package ru.android.myrecipesbook.model

import com.google.gson.annotations.SerializedName
import ru.android.myrecipesbook.enums.Meals

data class RecipesResponse(
    val page: Int?,
    val recipes: List<RecipeMock>? = null,
    @SerializedName("page_id")
    val pageId: Int?
)

data class RecipeMock(
    val id: Long?,
    val title: String?,
    val description: String?,
    @SerializedName("meal_type")
    val mealType: Meals?,
    @SerializedName("dish_type")
    val dishType: String?,
    @SerializedName("time_to_cook")
    val timeToCook: Long?,
    val rating: Float?,
    val calories: Int?,
    @SerializedName("is_favorite")
    val isFavorite: Boolean?,
    val portions: Int?,
    val category: String?,
    @SerializedName("image_url")
    val imageUr: String,
    val ingredients: List<Ingredient>,
    val steps: List<Steps>

)

data class Ingredient(
    val title: String?,
    @SerializedName("ingredient_alias")
    val ingredientAlias: String?,
    val count: Double?,
    val unit: String?,
    val unitCode: Int?,
    @SerializedName("ingredient_url")
    val ingredientUrl: String?,

    )

data class Steps(
    @SerializedName("step_number")
    val stepNumber: Int?,
    @SerializedName("step_description")
    val stepDescription: String?
)


