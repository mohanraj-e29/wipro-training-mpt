package com.example.homecontrollapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class RoomsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rooms)

        val room1Button = findViewById<Button>(R.id.room1Button)
        val room2Button = findViewById<Button>(R.id.room2Button)
        val hallButton = findViewById<Button>(R.id.hallButton)
        val kitchenButton = findViewById<Button>(R.id.kitchenButton)

        room1Button.setOnClickListener {
            navigateToRoomControl("room1") // Changed to "room1"
        }

        room2Button.setOnClickListener {
            navigateToRoomControl("room2") // Changed to "room2"
        }

        hallButton.setOnClickListener {
            navigateToRoomControl("hall") // Changed to "hall"
        }

        kitchenButton.setOnClickListener {
            navigateToRoomControl("kitchen") // Changed to "kitchen"
        }
    }

    private fun navigateToRoomControl(roomName: String) {
        val intent = Intent(this, RoomControlActivity::class.java)
        intent.putExtra("ROOM_NAME", roomName)
        startActivity(intent)
    }
}
