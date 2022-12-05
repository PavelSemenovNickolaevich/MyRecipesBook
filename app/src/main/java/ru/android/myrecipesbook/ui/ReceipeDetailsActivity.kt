package ru.android.myrecipesbook.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.android.myrecipesbook.databinding.ActivityReceipeDetailsBinding
import timber.log.Timber

class ReceipeDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReceipeDetailsBinding

    fun start(context: Context) {
        val intent = Intent(context, ReceipeDetailsActivity::class.java)
        context.startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReceipeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Timber.d("This is log for onCreate")
    }
}