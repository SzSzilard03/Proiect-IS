package com.myapplication

import android.os.Bundle
import android.widget.RelativeLayout
import androidx.activity.ComponentActivity


class MainPageActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainwindow)

        val textViewTeam = findViewById<RelativeLayout>(R.id.teambtn)
        val textViewLeague = findViewById<RelativeLayout>(R.id.league_btn)
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


