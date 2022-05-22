package uz.gita.vocabulary.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [
        EntityDict::class
    ],

    version = 1
)

abstract class AppDatabase() : RoomDatabase() {

    abstract fun getDao(): DictDao

    companion object {
        var database: AppDatabase? = null

        fun initDB(context: Context) {
            database = Room.databaseBuilder(context, AppDatabase::class.java, "dictionary.db")
                .allowMainThreadQueries()
                .createFromAsset("dictionary.db")
                .build()
        }

    }
}