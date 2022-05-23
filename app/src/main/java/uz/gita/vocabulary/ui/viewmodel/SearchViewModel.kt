package uz.gita.vocabulary.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.vocabulary.db.EntityDict
import uz.gita.vocabulary.repository.WordRepository

class SearchViewModel : ViewModel() {
    private var _wordLiveData = MutableLiveData<List<EntityDict>>()
    private val wordLiveData: LiveData<List<EntityDict>> = _wordLiveData
    private var _searchLiveData = MutableLiveData<List<EntityDict>>()
    private val searchLiveData:LiveData<List<EntityDict>> = _searchLiveData
    private lateinit var repository: WordRepository

    fun getAllWords(context: Context): LiveData<List<EntityDict>> {
        repository = WordRepository(context)
        _wordLiveData.value = repository.getAllWords()
        return wordLiveData
    }

    fun getSearchWord(searchWord: String) : LiveData<List<EntityDict>> {
        val searchList = repository.getSearchWord(searchWord)
        _searchLiveData.value = searchList
        return searchLiveData
    }

    fun updateWord(word:EntityDict?,context: Context) {
        val repository = WordRepository(context)
        repository.updateWord(word)
    }

}