package www.revengerfitness.blogspot.com.example.mvvmroomretrofit

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import www.revengerfitness.blogspot.com.example.mvvmroomretrofit.viewmodels.MainViewModel
import www.revengerfitness.blogspot.com.example.mvvmroomretrofit.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
val repository = (application as QuoteApplication).quoteRepository

        mainViewModel= ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]
        mainViewModel.quotes.observe(this) {

            Log.d("prashant chauhan", it.results.toString())

            Toast.makeText(this@MainActivity, it.results.size.toString(), Toast.LENGTH_SHORT).show()

        }
    }
}