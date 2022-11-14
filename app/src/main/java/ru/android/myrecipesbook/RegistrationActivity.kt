package ru.android.myrecipesbook

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import ru.android.myrecipesbook.databinding.ActivityRegistrationBinding
import timber.log.Timber

class RegistrationActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val MIN_NAME_SYMBOLS = 3
        const val MIN_EMAIL_SYMBOLS = 5
        const val MIN_PASSWORD_SYMBOLS = 6
    }

    private lateinit var binding: ActivityRegistrationBinding
    private val mainActivity: MainActivity = MainActivity()
    private val loginActivity: LoginActivity = LoginActivity()
    private val signInBtn: Button by lazy { binding.signInBtn }
    private val registrationBtn: Button by lazy { binding.registrationBtn }
    private val userName: TextInputEditText by lazy { binding.textInputName }
    private val email: TextInputEditText by lazy { binding.textInputEmail }
    private val password: TextInputEditText by lazy { binding.textInputPassword }

    fun start(context: WelcomeActivity) {
        val intent = Intent(context, RegistrationActivity::class.java)
        context.startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Timber.d("This is log for onCreate")

        registrationBtn.setOnClickListener(this)
        signInBtn.setOnClickListener(this)

        userName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                userName.text?.let {
                    if (it.length < MIN_NAME_SYMBOLS)
                        userName.error = resources.getString(R.string.wrong_name)
                }
            }
        })

        email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                email.text?.let {
                    if (it.length < MIN_EMAIL_SYMBOLS || !it.contains("@"))
                        email.error = resources.getString(R.string.wrong_email)
                }
            }
        })

        password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                password.text?.let {
                    if (it.length < MIN_PASSWORD_SYMBOLS)
                        password.error = resources.getString(R.string.wrong_password)
                }
            }
        })
    }

    override fun onClick(v: View?) {
        v?.let {
            when (it.id) {
                R.id.registrationBtn -> mainActivity.start(this)
                R.id.sign_in_btn -> loginActivity.start(this)
            }
        }
    }
}
