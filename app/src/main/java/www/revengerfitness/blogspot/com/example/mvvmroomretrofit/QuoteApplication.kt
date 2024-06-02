package www.revengerfitness.blogspot.com.example.mvvmroomretrofit

import android.app.Application
import www.revengerfitness.blogspot.com.example.mvvmroomretrofit.db.QuoteDatabase
import www.revengerfitness.blogspot.com.example.mvvmroomretrofit.repository.QuoteRepository
import www.revengerfitness.blogspot.com.example.mvvmroomretrofit.api.QuoteService
import www.revengerfitness.blogspot.com.example.mvvmroomretrofit.api.RetrofitHelper

class QuoteApplication :Application() {
lateinit var quoteRepository: QuoteRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
        val database = QuoteDatabase.getDatabase(applicationContext)
        quoteRepository = QuoteRepository(quoteService , database , applicationContext)
    }
}