package com.myapplication

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

val DATABASE_NAME = "my_database"

//TABLES
val PLAYERS_TABLE = "players"

//COLUMNS
val PLAYER_ID = "id"
val PLAYER_NAME = "name"
val PLAYER_AGE = "age"
val PLAYER_USERNAME = "username"
val PLAYER_EMAIL = "email"
val PLAYER_PASSWORD = "password"
val PLAYER_POSITION = "position"

class DataBaseHandler (context : Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1){

    override fun onCreate(p0: SQLiteDatabase?) {
        val createPlayersTable =
            "CREATE TABLE $PLAYERS_TABLE (" +
                    "$PLAYER_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$PLAYER_NAME VARCHAR(50), " +
                    "$PLAYER_AGE INTEGER, " +
                    "$PLAYER_USERNAME VARCHAR(50), " +
                    "$PLAYER_EMAIL VARCHAR(50), " +
                    "$PLAYER_PASSWORD VARCHAR(50), " +
                    "$PLAYER_POSITION VARCHAR(50))"
        p0?.execSQL(createPlayersTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
    fun insertData(player: Player){
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(PLAYER_NAME, player.name)
        cv.put(PLAYER_AGE, player.age)
        cv.put(PLAYER_USERNAME, player.username)
        cv.put(PLAYER_EMAIL, player.email)
        cv.put(PLAYER_PASSWORD, player.password)
        cv.put(PLAYER_POSITION, player.position)
        var res = db.insert(PLAYERS_TABLE, null, cv)//id este autoincrementat
        if(res.toInt() == -1)
            println("couldnt load db!")
        else println("successfully load db!")
    }
    @SuppressLint("Range")
    fun getAllPlayerNames(): List<String> {
        val namesList = mutableListOf<String>()
        val db = this.readableDatabase

        // Specify the columns you want to retrieve
        val columns = arrayOf(PLAYER_NAME)

        // Query the database to get all names
        val cursor = db.query(PLAYERS_TABLE, columns, null, null, null, null, null)

        // Iterate through the cursor and add names to the list
        cursor?.use {
            while (it.moveToNext()) {
                val playerName = it.getString(it.getColumnIndex(PLAYER_NAME))
                namesList.add(playerName)
            }
        }
        cursor?.close()
        db.close()
        return namesList
    }
}