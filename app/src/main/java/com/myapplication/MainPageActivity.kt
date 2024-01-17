package com.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.cardview.widget.CardView


class MainPageActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainwindow)


//        val textViewLeague = findViewById<RelativeLayout>(R.id.league_btn)
        // ... similarly for other CardViews
        val relativeLayoutTeam = findViewById<CardView>(R.id.teambtn)
        val relativeLayoutLeague = findViewById<CardView>(R.id.league_btn)
        val relativeLayoutFriends = findViewById<CardView>(R.id.friends_btn)
        val relativeLayoutProfile = findViewById<CardView>(R.id.profile_btn)
        val relativeLayoutFields = findViewById<CardView>(R.id.fields_btn)
        val relativeLayoutChat = findViewById<CardView>(R.id.chat_btn)

        relativeLayoutTeam.setOnClickListener {
            //showToast("ahoy")
            val intent = Intent(this, SignUpActivity::class.java)// TeamActivity
            startActivity(intent)
            finish()
        }

        relativeLayoutLeague.setOnClickListener {
            //showToast("League")
            val intent = Intent(this, SignUpActivity::class.java)// LeagueActivity
            startActivity(intent)
            finish()
        }

        relativeLayoutFriends.setOnClickListener {
            //showToast("Friends")
            val intent = Intent(this, SignUpActivity::class.java)// FriendsActivity
            startActivity(intent)
            finish()
        }

        relativeLayoutProfile.setOnClickListener {
            //showToast("Profile")
            val intent = Intent(this, SignUpActivity::class.java)// ProfileActivity
            startActivity(intent)
            finish()
        }

        relativeLayoutFields.setOnClickListener {
            //showToast("Fields")
            val intent = Intent(this, SignUpActivity::class.java)// FieldsActivity
            startActivity(intent)
            finish()
        }

        relativeLayoutChat.setOnClickListener {
            //showToast("Chat")
            val intent = Intent(this, SignUpActivity::class.java)// ChatActivity
            startActivity(intent)
            finish()
        }
        // ... set onClickListeners for other CardViews
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}


