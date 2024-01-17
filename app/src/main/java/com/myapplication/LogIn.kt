package com.myapplication

class LogIn(private val username: String, private val password: String){
    fun signInUser(): Boolean{
        val isValid = validateSignIn()
        return isValid
    }

    private fun validateSignIn(): Boolean {
        //placeholder
        return username.isNotBlank() && password.length >= 8
        //teoretic trebuie query la baza de date daca exista user si daca da, daca are match la parola
    }

}