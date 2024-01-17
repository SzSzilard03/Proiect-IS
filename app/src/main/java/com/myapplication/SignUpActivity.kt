package com.myapplication
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

class SignUpActivity :
    ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up)

        // Now you can continue with your logic, such as initializing views, handling user input, etc.
        val signUpInstance = SignUp("exampleUser", "password123", "user@example.com", "John Doe", "password123")
        val isSignUpSuccessful = signUpInstance.signUpUser()
        val backButton1 = findViewById<Button>(R.id.buttonBack1)
        val buttonSignUpAttempt = findViewById<Button>(R.id.buttonSignUpAttempt)
        backButton1.setOnClickListener {
            // Create an Intent to start the main activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            // Finish the current activity (SignUpActivity)
            finish()
        }


        if (isSignUpSuccessful) {
            println("Welcome! Your account has been created.")

        } else {
            println("Registration failed. Please try again.")
        }
        try {
            buttonSignUpAttempt.setOnClickListener {
                // Create an Intent to start the main activity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                // Finish the current activity (SignUpActivity)
                finish()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            // Log the exception or handle it appropriately
        }
    }
}