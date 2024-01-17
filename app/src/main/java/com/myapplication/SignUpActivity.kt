package com.myapplication
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
class SignUpActivity :
    AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.sign_up)

            // Now you can continue with your logic, such as initializing views, handling user input, etc.
            val signUpInstance = SignUp("exampleUser", "password123", "user@example.com")
            val isSignUpSuccessful = signUpInstance.signUpUser()

            if (isSignUpSuccessful) {
                println("Welcome! Your account has been created.")
            } else {
                println("Registration failed. Please try again.")
            }
        }
}