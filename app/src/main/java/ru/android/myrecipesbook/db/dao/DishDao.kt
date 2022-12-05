package ru.android.myrecipesbook.db.dao


import androidx.room.Insert
import androidx.room.Query
import ru.android.myrecipesbook.db.entity.DishEntity

@androidx.room.Dao
interface DishDao {

    @Insert
    fun saveFavoriteDish(dish: DishEntity)

    @Query("SELECT * FROM  dish")
    fun getFavoriteDish(): List<DishEntity>

    @Query("SELECT * FROM  dish WHERE isFavorite='1'")
    fun getFavoriteDishByLike(): List<DishEntity>

    @Query("DELETE FROM dish WHERE title=:title")
    fun deleteFavoriteDish(vararg title: String)
}