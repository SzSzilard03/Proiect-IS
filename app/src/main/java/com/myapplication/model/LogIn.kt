package com.myapplication.model

import android.content.Context
import android.widget.Toast
import com.myapplication.DataBaseHandler

class LogIn(private val context: Context, private val username: String, private val password: String) {
    private var db = DataBaseHandler(context = context)

    fun signInUser(): Boolean {
        val allUsers = db.getAllPlayerUsernames()
        println(allUsers)
        var usernameExists = false
        for (element in allUsers) {
            //println('!' + element + '!')
            //println('!' + username + '!')
            if (username.equals(element)) {
                // Username is already taken
                usernameExists = true

                break // Exit the loop since a match is found
            }
        }
        //println(usernameExists)
        if (usernameExists == false) {
            showToast("Username does not exist. Please try again with a different username.")
            return false
        }
        return validateSignIn()
    }

    private fun validateSignIn(): Boolean {
        if (username.isNotBlank() && password.length >= 8) {
            val temp = db.getPasswordByUsername(username)
            if (password.equals(temp)) {
                return true
            }
            return false
        } else {
            return false
        }
        // Placeholder
        // Teoretic trebuie query la baza de date daca exista user si daca da, daca are match la parola
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
