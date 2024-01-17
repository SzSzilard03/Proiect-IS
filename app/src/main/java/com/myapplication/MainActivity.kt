package com.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)
        val signUpButton = findViewById<Button>(R.id.buttonSignUp)
        val logInButton = findViewById<Button>(R.id.buttonLogIn)
        val txtUsername = findViewById<EditText>(R.id.username)
        val txtPass = findViewById<EditText>(R.id.password)
        var usernameString = txtUsername.toString()
        var passString = txtPass.toString()

        signUpButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        logInButton.setOnClickListener {
            val logInInstance = LogIn(usernameString, passString)
            val isLogInSuccessful = logInInstance.signInUser()
            if(isLogInSuccessful)
            {
                val intent = Intent(this, MainPageActivity::class.java)
                startActivity(intent)
            }
            else{
                showToast("Log in failed! Please check your data")
            }

        }

    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}