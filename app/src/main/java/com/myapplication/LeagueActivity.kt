package com.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.cardview.widget.CardView

class LeagueActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.league_front)
        val backBtn = findViewById<CardView>(R.id.back_btn)
        backBtn.setOnClickListener {
            //showToast("ahoy")
            val intent = Intent(this, MainPageActivity::class.java)// Return
            startActivity(intent)
            finish()
        }
        val fixturesBtn = findViewById<CardView>(R.id.fixtures_btn)
        fixturesBtn.setOnClickListener {
            //showToast("ahoy")
            val intent = Intent(this, MainPageActivity::class.java)// FixturesActivity
            startActivity(intent)
            finish()
        }
        val rankingBtn = findViewById<CardView>(R.id.ranking_btn)
        rankingBtn.setOnClickListener {
            //showToast("ahoy")
            val intent = Intent(this, MainPageActivity::class.java)// RankingActivity
            startActivity(intent)
            finish()
        }
        val TeamsBtn = findViewById<CardView>(R.id.teamsbtn)
        TeamsBtn.setOnClickListener {
            //showToast("ahoy")
            val intent = Intent(this, MainPageActivity::class.java)// TeamsActivity
            startActivity(intent)
            finish()
        }

    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}