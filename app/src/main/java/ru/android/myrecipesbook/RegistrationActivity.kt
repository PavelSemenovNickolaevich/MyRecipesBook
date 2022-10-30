package ru.android.myrecipesbook

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class RegistrationActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val MIN_NAME_SYMBOLS = 3
        const val MIN_EMAIL_SYMBOLS = 5
        const val MIN_PASSWORD_SYMBOLS = 6
    }

    fun start(context: Context) {
        val intent = Intent(context, RegistrationActivity::class.java)
        context.startActivity(intent)
    }

    private val mainActivity: MainActivity = MainActivity()
    private val loginActivity: LoginActivity = LoginActivity()
    val signInBtn: Button by lazy { findViewById(R.id.sign_in_btn) }
    val registrationBtn: ImageButton by lazy { findViewById(R.id.registrationBtn) }
    val userName: TextInputEditText by lazy { findViewById(R.id.text_input_name) }
    val email: TextInputEditText by lazy { findViewById(R.id.text_input_email) }
    val password: TextInputEditText by lazy { findViewById(R.id.text_input_password) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        registrationBtn.setOnClickListener(this)
        signInBtn.setOnClickListener(this)


        userName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if (userName.text!!.length < MIN_NAME_SYMBOLS)
                    userName.error = resources.getString(R.string.wrong_name)
            }
        })

        email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if (email.text!!.length < MIN_EMAIL_SYMBOLS || !email.text!!.contains("@"))
                    email.error = resources.getString(R.string.wrong_email)
            }
        })

        password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if (password.text!!.length < MIN_PASSWORD_SYMBOLS)
                    password.error = resources.getString(R.string.wrong_password)

            }
        })
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.registrationBtn -> mainActivity.start(this)
                R.id.sign_in_btn -> loginActivity.start(this)
            }
        }
    }

}
