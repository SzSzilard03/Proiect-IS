package com.myapplication

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

val DATABASE_NAME = "my_database"

//TABLES
val PLAYERS_TABLE = "players"
val TEAM_TABLE = "team"

//COLUMNS
val PLAYER_ID = "id"
val PLAYER_NAME = "name"
val PLAYER_AGE = "age"
val PLAYER_USERNAME = "username"
val PLAYER_EMAIL = "email"
val PLAYER_PASSWORD = "password"
val PLAYER_POSITION = "position"

val TEAM_ID = "team_id"
val TEAM_NAME = "team_name"
val TEAM_ADMIN_ID = "adminId"
val TEAM_PLAYER_IDS = "player_ids"

class DataBaseHandler (context : Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1){

    override fun onCreate(db: SQLiteDatabase?) {
        val createPlayersTable =
            "CREATE TABLE $PLAYERS_TABLE (" +
                    "$PLAYER_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$PLAYER_NAME VARCHAR(50), " +
                    "$PLAYER_AGE INTEGER, " +
                    "$PLAYER_USERNAME VARCHAR(50), " +
                    "$PLAYER_EMAIL VARCHAR(50), " +
                    "$PLAYER_PASSWORD VARCHAR(50), " +
                    "$PLAYER_POSITION VARCHAR(50))"

        val createTeamTable =
            "CREATE TABLE $TEAM_TABLE (" +
                    "$TEAM_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$TEAM_NAME VARCHAR(50), " +
                    "$TEAM_ADMIN_ID INTEGER, " +
                    "$TEAM_PLAYER_IDS VARCHAR(50), " +
                    "FOREIGN KEY($TEAM_PLAYER_IDS) REFERENCES $PLAYERS_TABLE($PLAYER_ID))"

        db?.execSQL(createPlayersTable)
        db?.execSQL(createTeamTable)
    }
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    //player queries:
    fun createPlayer(player: Player){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(PLAYER_NAME, player.name)
        cv.put(PLAYER_AGE, player.age)
        cv.put(PLAYER_USERNAME, player.username)
        cv.put(PLAYER_EMAIL, player.email)
        cv.put(PLAYER_PASSWORD, player.password)
        cv.put(PLAYER_POSITION, player.position)
        val res = db.insert(PLAYERS_TABLE, null, cv)//id este autoincrementat
        if(res.toInt() == -1)
            println("couldn't load db!")
        else println("successfully load db!")
    }
    @SuppressLint("Range")
    fun getPlayerDataByUsername(username: String): Player? {
        val db = this.readableDatabase
        val columns = arrayOf(PLAYER_ID, PLAYER_NAME, PLAYER_AGE, PLAYER_USERNAME, PLAYER_PASSWORD, PLAYER_EMAIL, PLAYER_POSITION)
        // WHERE clause
        val selection = "$PLAYER_USERNAME = ?"
        val selectionArgs = arrayOf(username)

        val cursor = db.query(PLAYERS_TABLE, columns, selection, selectionArgs, null, null, null)

        var player: Player? = null
        cursor.use {
            if (it.moveToFirst()) {
                val id = it.getInt(it.getColumnIndex(PLAYER_ID))
                val name = it.getString(it.getColumnIndex(PLAYER_NAME))
                val age = it.getInt(it.getColumnIndex(PLAYER_AGE))
                val pUsername = it.getString(it.getColumnIndex(PLAYER_USERNAME))
                val password = it.getString(it.getColumnIndex(PLAYER_PASSWORD))
                val email = it.getString(it.getColumnIndex(PLAYER_EMAIL))
                val position = it.getString(it.getColumnIndex(PLAYER_POSITION))
                player = Player(id, age, name, pUsername, password, email, position)
            }

        }
        cursor.close()
        db.close()
        if(player == null){
            println("couldn't retrieve player data!")
        }
        else{
            println("retrieved player data!")
        }
        return player
    }
    @SuppressLint("Range")
    fun getAllPlayerNames(): List<String> {
        val namesList = mutableListOf<String>()
        val db = this.readableDatabase
        val columns = arrayOf(PLAYER_NAME)
        val cursor = db.query(PLAYERS_TABLE, columns, null, null, null, null, null)

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
    @SuppressLint("Range")
    fun getAllPlayerUsernames(): List<String> {
        val namesList = mutableListOf<String>()
        val db = this.readableDatabase
        val columns = arrayOf(PLAYER_USERNAME)
        val cursor = db.query(PLAYERS_TABLE, columns, null, null, null, null, null)

        cursor?.use {
            while (it.moveToNext()) {
                val playerName = it.getString(it.getColumnIndex(PLAYER_USERNAME))
                namesList.add(playerName)
            }
        }
        cursor?.close()
        db.close()
        return namesList
    }
    @SuppressLint("Range")
    fun getPasswordByUsername(username: String): String? {
        val db = this.readableDatabase
        val columns = arrayOf(PLAYER_PASSWORD)
        val selection = "$PLAYER_USERNAME = ?"
        val selectionArgs = arrayOf(username)
        val cursor = db.query(PLAYERS_TABLE, columns, selection, selectionArgs, null, null, null)
        var password: String? = null

        cursor?.use {
            if (it.moveToFirst()) {
                password = it.getString(it.getColumnIndex(PLAYER_PASSWORD))
            }
        }
        cursor?.close()
        db.close()

        return password
    }
    @SuppressLint("Range")
    fun getPlayerIdByUsername(username: String): Int {
        val db = this.readableDatabase
        val columns = arrayOf(PLAYER_ID)
        val selection = "$PLAYER_USERNAME = ?"
        val selectionArgs = arrayOf(username)
        val cursor = db.query(PLAYERS_TABLE, columns, selection, selectionArgs, null, null, null)
        var playerId = -1 // Default value if the username is not found

        cursor.use {
            if (it.moveToFirst()) {
                playerId = it.getInt(it.getColumnIndex(PLAYER_ID))
            }
        }
        cursor.close()
        db.close()
        return playerId
    }

    //team queries:
    fun createTeam(team: Team): Long {
        val db = this.writableDatabase

        // Create a ContentValues object to store the data to be inserted
        val contentValues = ContentValues().apply {
            put(TEAM_NAME, team.teamName)
            put(TEAM_ADMIN_ID, team.adminId)
            put(TEAM_PLAYER_IDS, "") // Initial playerIds list is empty
        }

        // Insert the new team into the team table
        val res = db.insert(TEAM_TABLE, null, contentValues)
        db.close()
        // Return the row ID of the newly inserted row, or -1 if an error occurred
        if(res.toInt() == -1){
            println("couldn't store team into db!")
        }
        else {
            println("successfully stored team into db!")
        }
        return res
    }
    @SuppressLint("Range")
    fun retrieveTeamData(teamName: String): Team? {
        val db = this.readableDatabase
        val columns = arrayOf(TEAM_ID, TEAM_NAME, TEAM_ADMIN_ID, TEAM_PLAYER_IDS)
        // WHERE clause
        val selection = "$TEAM_NAME = ?"
        val selectionArgs = arrayOf(teamName)

        val cursor = db.query(TEAM_TABLE, columns, selection, selectionArgs, null, null, null)

        var team: Team? = null

        cursor.use {
            if (it.moveToFirst()) {
                val id = it.getInt(it.getColumnIndex(TEAM_ID))
                val name = it.getString(it.getColumnIndex(TEAM_NAME))
                val adminId = it.getInt(it.getColumnIndex(TEAM_ADMIN_ID))
                val playerIdsString = it.getString(it.getColumnIndex(TEAM_PLAYER_IDS))

                // Convert the playerIds string to a list of integers
                val playerIds = playerIdsString.split(",").map { playerIdString -> playerIdString.toInt() }.toMutableList()
                team = Team(id, name, adminId, playerIds)
            }
        }
        cursor.close()
        db.close()
        if (team == null) {
            println("Couldn't retrieve team data!")
        } else {
            println("Retrieved team data!")
        }
        return team
    }
    @SuppressLint("Range")
    fun getPlayerIdsForTeam(db: SQLiteDatabase, teamId: Int): MutableList<Int> {
        val existingPlayerIds = mutableListOf<Int>()

        // Specify the columns to retrieve
        val columns = arrayOf(TEAM_PLAYER_IDS)

        // Specify the selection criteria (WHERE clause)
        val selection = "$TEAM_ID = ?"

        // Specify the values for the selection criteria
        val selectionArgs = arrayOf(teamId.toString())

        // Query the database to get the playerIds list for the specified teamId
        val cursor = db.query(TEAM_TABLE, columns, selection, selectionArgs, null, null, null)

        cursor.use {
            if (it.moveToFirst()) {
                val playerIdsString = it.getString(it.getColumnIndex(TEAM_PLAYER_IDS))
                existingPlayerIds.addAll(playerIdsString.split(",").map { playerIdString -> playerIdString.toInt() })
            }
        }

        return existingPlayerIds
    }
    fun addPlayerToTeam(teamId: Int, playerId: Int): Boolean {
        val db = this.writableDatabase

        // Check if the team with the provided teamId exists
        if (teamExists(db, teamId)) {
            // Retrieve the existing playerIds list
            val existingPlayerIds = getPlayerIdsForTeam(db, teamId)

            // Add the new playerId to the list
            existingPlayerIds.add(playerId)

            // Update the playerIds list in the team table
            val contentValues = ContentValues().apply {
                put(TEAM_PLAYER_IDS, existingPlayerIds.joinToString(","))
            }

            val whereClause = "$TEAM_ID = ?"
            val whereArgs = arrayOf(teamId.toString())

            // Update the team table with the modified playerIds list
            val rowsAffected = db.update(TEAM_TABLE, contentValues, whereClause, whereArgs)

            db.close()

            // Return true if the update was successful
            return rowsAffected > 0
        } else {
            db.close()
            // Return false if the team with the provided teamId doesn't exist
            return false
        }
    }
    private fun teamExists(db: SQLiteDatabase, teamId: Int): Boolean {
        val query = "SELECT COUNT(*) FROM $TEAM_TABLE WHERE $TEAM_ID = ?"
        val cursor = db.rawQuery(query, arrayOf(teamId.toString()))

        val exists = cursor.use {
            it.moveToFirst()
            it.getInt(0) > 0
        }

        cursor.close()
        return exists
    }
    fun deletePlayerFromTeam(teamId: Int, playerId: Int): Boolean {
        val db = this.writableDatabase
        if (teamExists(db, teamId)) {
            val existingPlayerIds = getPlayerIdsForTeam(db, teamId)
            existingPlayerIds.remove(playerId)
            val contentValues = ContentValues().apply {
                put(TEAM_PLAYER_IDS, existingPlayerIds.joinToString(","))
            }
            val whereClause = "$TEAM_ID = ?"
            val whereArgs = arrayOf(teamId.toString())

            val rowsAffected = db.update(TEAM_TABLE, contentValues, whereClause, whereArgs)

            db.close()
            return rowsAffected > 0
        } else {
            db.close()
            return false
        }
    }

    //field queries:

}