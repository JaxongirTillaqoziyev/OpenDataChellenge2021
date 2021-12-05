package uz.abduvali.fightagainstcorruption.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.abduvali.fightagainstcorruption.database.dao.BookmarkDao
import uz.abduvali.fightagainstcorruption.database.dao.NewDao
import uz.abduvali.fightagainstcorruption.database.entity.BookmarkEntity
import uz.abduvali.fightagainstcorruption.database.entity.NewEntity

@Database(
    entities = [NewEntity::class, BookmarkEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newDao(): NewDao

    abstract fun bookmarkDao(): BookmarkDao

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context, AppDatabase::class.java, "my_db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}