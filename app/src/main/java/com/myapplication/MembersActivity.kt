package com.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.cardview.widget.CardView

class MembersActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.members_front)
        val backBtn = findViewById<CardView>(R.id.back_btn)
        val textSet = findViewById<TextView>(R.id.textViewData)
        val editTextData = findViewById<EditText>(R.id.editTextData)

        var addBtn = findViewById<CardView>(R.id.add_btn)
        var removeBtn = findViewById<CardView>(R.id.remove_btn)
        backBtn.setOnClickListener {
            //showToast("ahoy")
            val intent = Intent(this, MainPageActivity::class.java)// Return
            startActivity(intent)
            finish()
        }

        var db = DataBaseHandler(context = this)
        val temp = db.getPlayerDetails()
        if(temp.size<5)
        {
            db.populatePlayersTable()

        }
        val concatenatedString = temp.joinToString(separator = "\n")
        textSet.text = concatenatedString
        addBtn.setOnClickListener {
            var usernameString = editTextData.text.toString()
            //showToast("ahoy")
            println("NumeleMeu: " + usernameString)
            val allUsers = db.getAllPlayerUsernames()
            println("AllUsers: "+ allUsers)
            var usernameExists = false
            for (element in allUsers) {
                //println('!' + element + '!')
                //println('!' + username + '!')
                if (usernameString.equals(element)) {
                    // Username is already taken
                    usernameExists = true
                    var id = db.getPlayerIdByUsername(usernameString)
                    var idExists = false
                    for(elem in db.getPlayerIdsForTeam(1)){
                        if(id != elem){

                        }
                        else{
                            idExists = true
                            break
                        }

                    }
                    if (!idExists){
                        db.addPlayerToTeam(1, id)
                        showToast("Player added")
                        break // Exit the loop since a match is found
                    }
                    break // Exit the loop since a match is found


                }
            }
            //println(usernameExists)
            if (usernameExists == false) {
                showToast("Username does not exist. Please try again with a different username.")
            }
        }
        removeBtn.setOnClickListener {
            //showToast("ahoy")
            val allUsers = db.getAllPlayerUsernames()
            var usernameString = editTextData.text.toString()
            var usernameExists = false
            for (element in allUsers) {
                //println('!' + element + '!')
                //println('!' + username + '!')
                var id = db.getPlayerIdByUsername(usernameString)
                var idExists = false
                for(elem in db.getPlayerIdsForTeam(1)){
                    if(id != elem){

                    }
                    else{
                        idExists = true
                        break
                    }

                }
                if (idExists){
                    db.deletePlayerFromTeam(1, id)
                    showToast("Player removed")
                    break // Exit the loop since a match is found
                }
                break // Exit the loop since a match is found
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