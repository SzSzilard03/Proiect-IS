package com.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.cardview.widget.CardView

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.account_page)
        val backBtn = findViewById<CardView>(R.id.back_btn)
        val Data_list = findViewById<TextView>(R.id.Data_list)
        backBtn.setOnClickListener {
            //showToast("ahoy")
            val intent = Intent(this, MainPageActivity::class.java)// Return
            startActivity(intent)
            finish()
        }
        var db = DataBaseHandler(context = this)
        val temp = db.getPlayerDataByUsername("a")
        val user = temp?.username
        val nume = temp?.name
        val age = temp?.age
        val pos = temp?.position
        val mail = temp?.email
        if (age == null || user.equals(null) || nume.equals(null) || pos.equals(null) || mail.equals(null) ){

        }
        else{
            val concatenatedString = "username: " + user + "\n" +
                    "name: " + nume + "\n" +"age: " + age + "\n" +"position:" + pos + "\n" +"mail: " + mail
            Data_list.text = concatenatedString
        }

    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}