package com.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.cardview.widget.CardView

class FriendsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val usernameEditText = findViewById<EditText>(R.id.search)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.friends_front)
        val backBtn = findViewById<CardView>(R.id.back_btn)
        backBtn.setOnClickListener {
            //showToast("ahoy")
            val intent = Intent(this, MainPageActivity::class.java)// Return
            startActivity(intent)
            finish()
        }
        val AddfrndBtn = findViewById<CardView>(R.id.add_btn)

        AddfrndBtn.setOnClickListener{
            //addfriendlogic\
            val username = usernameEditText.text.toString()
            var db = DataBaseHandler(context = this)
            val allUsers = db.getAllPlayerUsernames()
            println(allUsers)
            var usernameExists = false
            for (element in allUsers) {
                //println('!' + element + '!')
                //println('!' + username + '!')
                if (username.equals(element)) {
                    usernameExists = true//cerere

                    break // Exit the loop since a match is found
                }
            }
            //println(usernameExists)
            if (usernameExists == false) {
                showToast("Username does not exist. Please try again with a different username.")

            }
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}