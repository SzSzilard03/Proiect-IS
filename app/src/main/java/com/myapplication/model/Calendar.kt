package com.myapplication.model

class Calendar {
    var calendarId: Int = 0
    var userId: Int = 0
    var fixtures: MutableList<Fixture> = mutableListOf()

    inner class Date(val day: Int, val month: Int, val year: Int)

    inner class Fixture(
        val team1: Team = Team(0, "manu", 0),
        val team2: Team = Team(0, "manc", 1),
        val date: Date = Date(1, 1, 2023)
    )
}
