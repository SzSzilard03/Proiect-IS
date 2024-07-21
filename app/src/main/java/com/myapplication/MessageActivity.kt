package com.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.model.Message
import com.myapplication.ui.MessageAdapter

class MessageActivity : AppCompatActivity() {

    private lateinit var messageAdapter: MessageAdapter
    private lateinit var messageList: MutableList<Message>
    private lateinit var recyclerView: RecyclerView
    private lateinit var messageInput: EditText
    private lateinit var sendButton: Button

    private lateinit var db: DataBaseHandler
    private var currentUser: String? = null

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

        db = DataBaseHandler(this)

        // Fetch the current user's username
        currentUser = db.getPlayerDataByUsername("current_user_username")?.username

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
                senderName = currentUser ?: "Unknown",  // Use the retrieved username
                recipientId = "group",
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
