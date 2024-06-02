package www.revengerfitness.blogspot.com.example.mvvmroomretrofit.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import www.revengerfitness.blogspot.com.example.mvvmroomretrofit.models.Result

@Database(entities = [Result::class], version = 1)
abstract class QuoteDatabase : RoomDatabase() {

    abstract fun quoteDao(): QuoteDao

    companion object {


        //implementing singleton pattern for making database instance
        @Volatile//jaisay he Instance main koi value aati hai wo saaray threads ko available ho jaati hai or update sab tak pahuch jaati hai.
        private var Instance: QuoteDatabase? = null//common pattern for singleton

        fun getDatabase(context: Context): QuoteDatabase {// Instance is private field so we need public method to expose it.
            if (Instance == null) {//agr null nahi hai then create instance.
                /**
                 * In android we make multiple threads for different operations so there is possibility
                 * that two threads will try to create this object at the same time so what happen
                 * there will we multiple database instance so in order to fix we use locking by synchronous block*/
                synchronized(this) {
                    Instance = Room.databaseBuilder(
                        context.applicationContext,
                        QuoteDatabase::class.java,
                        "contactDB"
                    )
                        .build()
                }


            }
            return Instance!!//instance is non nullable so we use double exclamation.
        }
    }
}