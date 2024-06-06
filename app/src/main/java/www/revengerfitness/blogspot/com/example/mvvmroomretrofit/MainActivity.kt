package www.revengerfitness.blogspot.com.example.mvvmroomretrofit

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import www.revengerfitness.blogspot.com.example.mvvmroomretrofit.databinding.ActivityMainBinding
import www.revengerfitness.blogspot.com.example.mvvmroomretrofit.models.Result
import www.revengerfitness.blogspot.com.example.mvvmroomretrofit.viewmodels.MainViewModel
import www.revengerfitness.blogspot.com.example.mvvmroomretrofit.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
val repository = (application as QuoteApplication).quoteRepository

        mainViewModel= ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager= LinearLayoutManager(this)
        binding.recyclerView.layoutManager=binding.recyclerView.layoutManager
        mainViewModel.quotes.observe(this) {

            Log.d("prashant chauhan", it.results.toString())

            Toast.makeText(this@MainActivity, it.results.size.toString(), Toast.LENGTH_SHORT).show()

            binding.recyclerView.adapter = QuoteAdapter( it.results as MutableList<Result>)
            binding.recyclerView.adapter!!.notifyDataSetChanged()
            binding.recyclerView.adapter = binding.recyclerView.adapter


        }
    }


}