package ru.android.myrecipesbook

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.android.myrecipesbook.databinding.ActivityLoginBinding
import timber.log.Timber

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    fun start(context: Context) {
        val intent = Intent(context, LoginActivity::class.java)
        context.startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Timber.d("This is log for onCreate")
    }

}