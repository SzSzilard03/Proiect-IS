package com.myapplication.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.DataBaseHandler
import com.myapplication.R
import com.myapplication.model.Message

class MessageAdapter(private val messages: List<Message>) :
    RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_message, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages[position]
        holder.bind(message)
    }

    override fun getItemCount(): Int = messages.size

    class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val messageTextView: TextView = itemView.findViewById(R.id.text_message)
        private val timestampTextView: TextView = itemView.findViewById(R.id.text_timestamp)
        private val senderTextView: TextView = itemView.findViewById(R.id.text_sender)  // Add this

        fun bind(message: Message) {
            messageTextView.text = message.content
            timestampTextView.text = android.text.format.DateFormat.format("hh:mm", message.timestamp)
            senderTextView.text = message.senderName  // Bind sender name here
        }
    }
}
