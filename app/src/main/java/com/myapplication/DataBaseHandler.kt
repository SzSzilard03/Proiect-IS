package com.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

val DATABASE_NAME = "my_database"
val TABLE_NAME = "Players"
val COL_NAME = "username"
val COL_AGE = "age"
val COL_ID = "id"
class DataBaseHandler (context : Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1){
    override fun onCreate(p0: SQLiteDatabase?) {
        val createTable =
            "CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_NAME VARCHAR(256), $COL_AGE INTEGER)"
        p0?.execSQL(createTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
    fun insertData(player: Player){
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_NAME, player.name)
        cv.put(COL_AGE, player.age)
        var res = db.insert(TABLE_NAME, null, cv)
        if(res.toInt() == -1)
            println("couldnt load db!")
        else println("successfully load db!")
    }
}