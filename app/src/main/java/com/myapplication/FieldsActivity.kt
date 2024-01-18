package com.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.cardview.widget.CardView

class FieldsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fields_front)
        val backBtn = findViewById<CardView>(R.id.back_btn)
        val textSet = findViewById<TextView>(R.id.textViewData)
        backBtn.setOnClickListener {
            //showToast("ahoy")
            val intent = Intent(this, MainPageActivity::class.java)// Return
            startActivity(intent)
            finish()
        }

        var db = DataBaseHandler(context = this)
        val temp = db.getAllFieldNames()
        if(temp.isEmpty())
        {
            db.populateFieldTable()
        }
        val concatenatedString = temp.joinToString(separator = "\n")
        textSet.text = concatenatedString
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}