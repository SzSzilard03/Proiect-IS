package com.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.model.Message
import com.myapplication.R
import com.yourapp.ui.MessageAdapter


class MessageActivity : AppCompatActivity() {

    private lateinit var messageAdapter: MessageAdapter
    private lateinit var messageList: MutableList<Message>
    private lateinit var recyclerView: RecyclerView
    private lateinit var messageInput: EditText
    private lateinit var sendButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)

        messageList = mutableListOf()

        recyclerView = findViewById(R.id.recycler_view)
        messageInput = findViewById(R.id.edit_message)
        sendButton = findViewById(R.id.button_send)

        messageAdapter = MessageAdapter(messageList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = messageAdapter

        sendButton.setOnClickListener {
            sendMessage()
        }
    }

    private fun sendMessage() {
        val content = messageInput.text.toString()
        if (content.isNotEmpty()) {
            val message = Message(
                id = generateMessageId(),
                senderId = "currentUserId",  // Replace with actual sender ID
                recipientId = "recipientId", // Replace with actual recipient ID
                content = content,
                timestamp = System.currentTimeMillis()
            )
            messageList.add(message)
            messageAdapter.notifyItemInserted(messageList.size - 1)
            recyclerView.scrollToPosition(messageList.size - 1)
            messageInput.text.clear()

            // TODO: Send message to backend or server
        }
    }

    private fun generateMessageId(): String {
        // Implement a method to generate a unique message ID
        return java.util.UUID.randomUUID().toString()
    }
}
