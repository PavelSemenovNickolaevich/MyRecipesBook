package ru.android.myrecipesbook.db

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import ru.android.myrecipesbook.db.dao.DishDao
import ru.android.myrecipesbook.db.entity.DishEntity

@Database(
    entities = [DishEntity::class],
    version = 8,
    exportSchema = true,
    autoMigrations = [AutoMigration(from = 7, to = 8)]
)
abstract class RecipesDatabase : RoomDatabase() {

    companion object {
        private var INSTANCE: RecipesDatabase? = null

        fun getDatabase(context: Context): RecipesDatabase? {
            if (INSTANCE == null) {
                synchronized(RecipesDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        RecipesDatabase::class.java, "recipes_dish"
                    ).allowMainThreadQueries()
                        .addMigrations(MIGRATION_7_8)
                        .build()
                }
            }
            return INSTANCE
        }
    }

    abstract fun dishes(): DishDao

}

@VisibleForTesting
internal val MIGRATION_7_8 = object : Migration(7, 8) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE dish ADD COLUMN isFavorite INTEGER")
    }
}