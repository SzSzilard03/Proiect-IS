package com.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.myapplication.model.LogIn


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)
        val signUpButton = findViewById<Button>(R.id.buttonSignUp)
        val logInButton = findViewById<Button>(R.id.buttonLogIn)
        val txtUsername = findViewById<EditText>(R.id.username)
        val txtPass = findViewById<EditText>(R.id.password)


        signUpButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
        logInButton.setOnClickListener {
            var usernameString = txtUsername.text.toString()
            var passString = txtPass.text.toString()
            //var db = DataBaseHandler(context = this)
            val logInInstance = LogIn(context = this, usernameString, passString)
            val isLogInSuccessful = logInInstance.signInUser()
            if(isLogInSuccessful)
            {
                val intent = Intent(this, MainPageActivity::class.java)
                startActivity(intent)
                finish()
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