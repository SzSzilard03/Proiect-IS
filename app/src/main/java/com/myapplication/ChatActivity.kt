package com.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.cardview.widget.CardView

class ChatActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.message_front)
        val backBtn = findViewById<CardView>(R.id.back_btn)
        backBtn.setOnClickListener {
            //showToast("ahoy")
            val intent = Intent(this, MainPageActivity::class.java)// Return
            startActivity(intent)
            finish()
        }
        val teamChat = findViewById<CardView>(R.id.team_btn)
        teamChat.setOnClickListener {
            val intent = Intent(this, MessageActivity::class.java)  // Start MessageActivity
            startActivity(intent)
        }
        val friends_btn = findViewById<CardView>(R.id.friends_btn)
        friends_btn.setOnClickListener {
            //showToast("ahoy")
            val intent = Intent(this, MessageActivity::class.java)  // Start MessageActivity
            startActivity(intent)
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}