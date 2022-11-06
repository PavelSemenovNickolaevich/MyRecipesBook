package ru.android.myrecipesbook

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber


class WelcomeActivity : AppCompatActivity(), View.OnClickListener {

    private val registrationActivity: RegistrationActivity = RegistrationActivity()
    private val loginActivity: LoginActivity = LoginActivity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        Timber.d("This is log for on Create")

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

