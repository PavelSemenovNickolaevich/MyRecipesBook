package ru.android.myrecipesbook

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

class MainActivity : AppCompatActivity() {


    fun start(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.d("This is log for onCreate")

        var recipeBtn:ImageButton = findViewById(R.id.recipe_1_btn)

        recipeBtn.setOnClickListener{
            val intent = Intent(this, ReceipeDetailsActivity::class.java )
            startActivity(intent)
        }
    }
}