package com.myapplication


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.cardview.widget.CardView
class TeamActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.team_front)
        val backBtn = findViewById<CardView>(R.id.back_btn)
        backBtn.setOnClickListener {
            //showToast("ahoy")
            val intent = Intent(this, MainPageActivity::class.java)// Return
            startActivity(intent)
            finish()
        }
        val resultsBtn = findViewById<CardView>(R.id.results_btn)
        resultsBtn.setOnClickListener {
            //showToast("ahoy")
            val intent = Intent(this, MainPageActivity::class.java)// ResultsActivity
            startActivity(intent)
            finish()
        }
        val chatBtn = findViewById<CardView>(R.id.chat_btn)
        chatBtn.setOnClickListener {
            //showToast("ahoy")
            val intent = Intent(this, MainPageActivity::class.java)// ChatActivity
            startActivity(intent)
            finish()
        }
        val fixturesBtn = findViewById<CardView>(R.id.fixtures_btn)
        fixturesBtn.setOnClickListener {
            //showToast("ahoy")
            val intent = Intent(this, MainPageActivity::class.java)// MembersActivity
            startActivity(intent)
            finish()
        }
        val membersBtn = findViewById<CardView>(R.id.members_btn)
        membersBtn.setOnClickListener {
            //showToast("ahoy")
            val intent = Intent(this, MembersActivity::class.java)// FixturesActivity
            startActivity(intent)
            finish()
        }

    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}