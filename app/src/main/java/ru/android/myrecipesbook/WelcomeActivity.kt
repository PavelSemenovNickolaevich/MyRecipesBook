package ru.android.myrecipesbook

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import ru.android.myrecipesbook.databinding.ActivityWelcomeBinding
import timber.log.Timber


class WelcomeActivity : AppCompatActivity(), View.OnClickListener {

    private val registrationActivity: RegistrationActivity = RegistrationActivity()
    private val loginActivity: LoginActivity = LoginActivity()
    private lateinit var binding: ActivityWelcomeBinding
    private val registrationBtn: Button by lazy { binding.registrationBtn }
    private val loginBtn: Button by lazy { binding.loginBtn }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Timber.d("This is log for onCreate")

        val registrationBtn = findViewById<Button>(R.id.registrationBtn)
        val loginBtn = findViewById<Button>(R.id.loginBtn)

        registrationBtn.setOnClickListener(this)
        loginBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        v?.let {
            when (it.id) {
                R.id.registrationBtn -> registrationActivity.start(this)
                R.id.loginBtn -> loginActivity.start(this)
            }
        }
    }
}

