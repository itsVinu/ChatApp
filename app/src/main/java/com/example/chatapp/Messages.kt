package com.example.chatapp

data class Messages (
    val from : String,
    val time : Long,
    val msg : String,
    val email : String
){
    constructor():this("",0L,"","")
}
