package com.app.wizardsapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.wizardsapp.utils.Convertors

@TypeConverters(Convertors::class)
@Database(entities = [Wizards::class, ElixirsByIdData::class, WizardsByIdData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun wizardsDao(): WizardsDoa

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}