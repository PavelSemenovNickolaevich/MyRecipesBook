package ru.android.myrecipesbook.repository

import android.content.Context
import ru.android.myrecipesbook.db.RecipesDatabase
import ru.android.myrecipesbook.db.dao.DishDao
import ru.android.myrecipesbook.db.entity.DishEntity

class DishRepository(context: Context) {

    private var db: DishDao = RecipesDatabase.getDatabase(context)?.dishes()!!

    suspend fun saveFavoriteDish(dish: DishEntity) {
        db.saveFavoriteDish(dish)
    }

    suspend fun deleteFavoriteDish(dishName: String) {
        db.deleteFavoriteDish(dishName)
    }

    suspend fun getFavoriteDishRepo(): List<DishEntity> {
        return db.getFavoriteDish()
    }

     suspend fun getFavoriteDishByLike(): List<DishEntity> {
        return db.getFavoriteDishByLike()
    }

}