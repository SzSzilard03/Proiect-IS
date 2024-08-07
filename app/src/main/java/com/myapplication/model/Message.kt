package com.myapplication.model

data class Message(
    val id: String,
    val senderId: String,
    val senderName: String,
    val recipientId: String,
    val content: String,
    val timestamp: Long
)