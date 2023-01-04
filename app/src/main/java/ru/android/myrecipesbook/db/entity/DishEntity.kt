package ru.android.myrecipesbook.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dish")
data class DishEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "rating")
    val rating: Float?,
    @ColumnInfo(name = "calories")
    val calories: String?,
    @ColumnInfo(name = "isFavorite")
    val isFavorite: Boolean?,
//    val portions: Int?,
//    val category: String?,
//    val imageUrl: String?
    //    @Ignore
//    @SerializedName("meal_type")
//    val mealType: Meals?,
//    @SerializedName("time_to_cook")
//    val timeToCook: Long?,

)
