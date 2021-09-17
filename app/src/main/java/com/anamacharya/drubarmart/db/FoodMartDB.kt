package com.anamacharya.drubarmart.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.anamacharya.drubarmart.dob.ProductDAO
import com.anamacharya.drubarmart.entity.Product

@Database(
    entities = [(Product::class)],
    version = 2,
    exportSchema = false
)
abstract class FoodMartDB : RoomDatabase() {
  abstract fun getProductDAO(): ProductDAO

    companion object {
        @Volatile
        private var instance: FoodMartDB? = null

        fun getInstance(context: Context): FoodMartDB {
            if (instance == null) {
                synchronized(FoodMartDB::class) {
                    instance = buildDatabase(context)
                }
            }
            return instance!!
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                FoodMartDB::class.java,
                "FoodMart"
            ).build()
    }
}
