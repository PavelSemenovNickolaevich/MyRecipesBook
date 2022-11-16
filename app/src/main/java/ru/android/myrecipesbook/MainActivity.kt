package ru.android.myrecipesbook

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView
import ru.android.myrecipesbook.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    fun start(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Timber.d("This is log for on Create")

        val navigationView: NavigationBarView = binding.navigationView

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container_view, SearchFragment())
            .addToBackStack(SearchFragment::class.java.canonicalName)
            .commit()

        navigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.search_fragment -> {
                    loadFragment(SearchFragment())
                    true
                }
                R.id.my_recipes_fragment -> {
                    loadFragment(FavoriteFragment())
                    true
                }
                R.id.profile_fragment -> {
                    loadFragment(ProfileFragment())
                    true
                }
                else -> false
            }

        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container_view, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}