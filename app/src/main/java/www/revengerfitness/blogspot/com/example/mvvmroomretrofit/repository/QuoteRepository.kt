package www.revengerfitness.blogspot.com.example.mvvmroomretrofit.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import www.revengerfitness.blogspot.com.example.mvvmroomretrofit.db.QuoteDatabase
import www.revengerfitness.blogspot.com.example.mvvmroomretrofit.utils.NetworkUtils
import www.revengerfitness.blogspot.com.example.mvvmroomretrofit.api.QuoteService
import www.revengerfitness.blogspot.com.example.mvvmroomretrofit.models.QuoteList

class QuoteRepository(
    private val quoteService: QuoteService,
    private val quoteDatabase: QuoteDatabase,
    private val applicationContext: Context
) {
private val quotesLiveData=MutableLiveData<QuoteList>()
    val quotes:LiveData<QuoteList>
        get() = quotesLiveData
    suspend fun getQuotes(page:Int){

        if (NetworkUtils.isInternetAvailable(applicationContext)) {

            val result = quoteService.getQuotes(page)
            if (result.body() !=null){
                quoteDatabase.quoteDao().addQuotes(result.body()!!.results) // storing quotes in database
                quotesLiveData.postValue(result.body())

            }

        } else {

            val quotes = quoteDatabase.quoteDao().getQuotes()
            val quoteList = QuoteList(1, 1, 1, quotes, 1, 1) // offline mode
            quotesLiveData.postValue(quoteList)
        }


    }
}