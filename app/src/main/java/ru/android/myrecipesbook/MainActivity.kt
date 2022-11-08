package ru.android.myrecipesbook

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import ru.android.myrecipesbook.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val filterBtn: ImageButton by lazy { binding.filterBtn }

    fun start(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Timber.d("This is log for onCreate")

        filterBtn.setOnClickListener{
            val intent = Intent(this, FilterActivity::class.java )
            startActivity(intent)
        }
    }
}