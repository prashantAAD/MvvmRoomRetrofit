package www.revengerfitness.blogspot.com.example.mvvmroomretrofit.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import www.revengerfitness.blogspot.com.example.mvvmroomretrofit.models.Result

@Dao
interface QuoteDao {

    //room provide these functions implementation.
    //if suspend than it will run on background thread through coroutines.
    @Insert
    suspend fun addQuotes(quotes: kotlin.collections.List<www.revengerfitness.blogspot.com.example.mvvmroomretrofit.models.Result>)

    //if function is livedata then it will automatically run on background thread.
    @Query("SELECT * FROM quote")
   suspend fun getQuotes(): List<Result>

}
