package com.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import android.content.Context
class SignUpActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up)

        val usernameEditText = findViewById<EditText>(R.id.editTextUserNameCreate)
        val passwordEditText = findViewById<EditText>(R.id.editTextPassCreate)
        val emailEditText = findViewById<EditText>(R.id.editTextEmailCreate)
        val fullNameEditText = findViewById<EditText>(R.id.editTextNameCreate)
        val confirmPasswordEditText = findViewById<EditText>(R.id.editTextPassConf)

        val backButton1 = findViewById<Button>(R.id.buttonBack1)
        val buttonSignUpAttempt = findViewById<Button>(R.id.buttonSignUpAttempt)

        backButton1.setOnClickListener {
            // Create an Intent to start the main activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            // Finish the current activity (SignUpActivity)
            finish()
        }

        buttonSignUpAttempt.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            val email = emailEditText.text.toString()
            val fullName = fullNameEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()

            val signUpInstance = SignUp(username, password, email, fullName, confirmPassword)
            val isSignUpSuccessful = signUpInstance.signUpUser()

            if (isSignUpSuccessful) {

                var db = DataBaseHandler(context = this)
                val temp = Player(fullName, 20)
                db.insertData(temp)
                showToast("Welcome! Your account has been created.")

                // Create an Intent to start the main activity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                // Finish the current activity (SignUpActivity)
                finish()
            } else {
                showToast("Registration failed. Please try again.")
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
