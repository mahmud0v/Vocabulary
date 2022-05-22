package uz.gita.vocabulary.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update

@Dao
interface DictDao {


    @Query("select *from eng_uz")
    fun getAll(): List<EntityDict>

    @Query("select *from  eng_uz where en_word like :str")
    fun engSearch(str: String?): List<EntityDict>

    @Query("select *from  eng_uz where countable like :str")
    fun historyWord(str: String?): List<EntityDict>

    @Query("select *from  eng_uz where favourite like :str")
    fun bookmarkWord(str: String?): List<EntityDict>

    @Update
    fun updateWord(word: EntityDict)


}