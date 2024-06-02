package www.revengerfitness.blogspot.com.example.mvvmroomretrofit.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import www.revengerfitness.blogspot.com.example.mvvmroomretrofit.models.QuoteList
import www.revengerfitness.blogspot.com.example.mvvmroomretrofit.repository.QuoteRepository

class MainViewModel(private val repository: QuoteRepository):ViewModel(){

    init {
        viewModelScope.launch (Dispatchers.IO){
            repository.getQuotes(1)
        }

    }

    val quotes : LiveData<QuoteList>
        get() = repository.quotes
}