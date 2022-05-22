package uz.gita.vocabulary.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.vocabulary.db.EntityDict
import uz.gita.vocabulary.repository.WordRepository

class BookmarkViewModel : ViewModel() {

    private var _bookmarkLiveData = MutableLiveData<List<EntityDict>>()
    private val bookmarkLiveData:LiveData<List<EntityDict>> = _bookmarkLiveData


    fun bookmarkWords(context: Context, str:String) : LiveData<List<EntityDict>>{
        val repository = WordRepository(context)
        _bookmarkLiveData.value = repository.bookmarkWord(str)
        return bookmarkLiveData
    }

}