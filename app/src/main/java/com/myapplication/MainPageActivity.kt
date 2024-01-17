package com.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout

class MainPageActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    private lateinit var toolbar: Toolbar
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainwindow)

        // Hooks
        drawerLayout = findViewById(R.id.drawer_layout)
        //navigationView = findViewById(R.id.nav_view)
        textView = findViewById(R.id.textView)






    }


}