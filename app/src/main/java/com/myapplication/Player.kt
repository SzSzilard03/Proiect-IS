package com.myapplication

class Player {
    var id : Int = 0
    var age : Int = 0
    var name : String = ""
    var username : String = ""
    var password : String = ""
    var email : String = ""
    var position : String = "CM"

    constructor(name: String, username: String, password: String, email: String){
        this.name = name
        this.username = username
        this.password = password
        this.email = email
    }
    constructor(id: Int, age:Int, name: String, username: String, password: String, email: String, position:String){
        this.id = id
        this.age = age
        this.name = name
        this.username = username
        this.password = password
        this.email = email
        this.position = position
    }
}