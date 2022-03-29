package io.henrikhorbovyi.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.henrikhorbovyi.data.source.local.entity.Host

@Database(
    entities = [Host::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun hostDao(): HostDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        private const val DB_NAME = "I_shelter_you.db"

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = buildDatabase(context)
                INSTANCE = instance
                instance
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                DB_NAME
            )
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}