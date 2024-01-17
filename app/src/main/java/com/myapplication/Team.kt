package com.myapplication

class Team {
    var id : Int = 0
    var teamName : String = ""
    var adminId : Int = 0
    var playerIds : MutableList<Int> = mutableListOf()
    constructor(id:Int, teamName:String, adminId:Int){
        this.id = id
        this.teamName = teamName
        this.adminId = adminId
    }
}