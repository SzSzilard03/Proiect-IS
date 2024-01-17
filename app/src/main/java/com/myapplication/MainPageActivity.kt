package com.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity


class MainPageActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainwindow)

        val textViewTeam = findViewById<TextView>(R.id.Team)
        val textViewLeague = findViewById<TextView>(R.id.League)
        // ... similarly for other CardViews

        textViewTeam.setOnClickListener {
            // Handle click for "Team"
        }

        textViewLeague.setOnClickListener {
            // Handle click for "League"
        }

        // ... set onClickListeners for other CardViews
    }
    }


