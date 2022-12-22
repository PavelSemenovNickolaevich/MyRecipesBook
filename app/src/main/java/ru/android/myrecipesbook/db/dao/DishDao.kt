package ru.android.myrecipesbook.db.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.android.myrecipesbook.db.entity.DishEntity

@Dao
interface DishDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFavoriteDish(dish: DishEntity)

    @Query("SELECT * FROM  dish")
    fun getFavoriteDish(): List<DishEntity>

    @Query("SELECT * FROM  dish WHERE isFavorite='1'")
    fun getFavoriteDishByLike(): List<DishEntity>

    @Query("DELETE FROM dish WHERE title=:title")
    fun deleteFavoriteDish(vararg title: String)
}