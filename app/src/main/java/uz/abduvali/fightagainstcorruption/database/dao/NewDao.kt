package uz.abduvali.fightagainstcorruption.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import uz.abduvali.fightagainstcorruption.database.entity.NewEntity

@Dao
interface NewDao {

    @Insert(onConflict = REPLACE)
    fun addNew(newEntity: NewEntity)

//    @Insert(onConflict = REPLACE)
//    fun addNews(list: List<NewEntity>)

    @Query("select * from newentity")
    fun getNews(): List<NewEntity>
}