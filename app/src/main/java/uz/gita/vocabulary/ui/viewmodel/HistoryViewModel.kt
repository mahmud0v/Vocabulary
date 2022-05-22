package uz.gita.vocabulary.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.vocabulary.db.EntityDict
import uz.gita.vocabulary.repository.WordRepository

class HistoryViewModel : ViewModel() {

    private var _historyLiveData = MutableLiveData<List<EntityDict>>()
    private val historyLiveData:LiveData<List<EntityDict>> = _historyLiveData

    fun getHistoryWords(context:Context,historyWord:String) : LiveData<List<EntityDict>>{
        val repository = WordRepository(context)
        _historyLiveData.value = repository.historyWord(historyWord)
        return historyLiveData
    }


}