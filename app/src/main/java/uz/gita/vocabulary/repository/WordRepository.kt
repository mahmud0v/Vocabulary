package uz.gita.vocabulary.repository

import android.content.Context
import uz.gita.vocabulary.db.AppDatabase
import uz.gita.vocabulary.db.EntityDict

class WordRepository(context:Context) {

    private val database = AppDatabase.initDB(context)
    private val wordDao = AppDatabase.database!!.getDao()


    fun getAllWords() = wordDao.getAll()


    fun getSearchWord(searchWord:String) = wordDao.engSearch(searchWord)


    fun historyWord(historyKey:String) = wordDao.historyWord(historyKey)

    fun bookmarkWord(rate:String) = wordDao.bookmarkWord(rate)


    fun updateWord(word:EntityDict?) = wordDao.updateWord(word)


}