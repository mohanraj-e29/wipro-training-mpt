package com.example.homecontrollapp;

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val appIcon = findViewById<ImageView>(R.id.appIcon)
        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username == "mohanraj" && password == "8900") {
                // Login successful, show toast message
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()

                // Start RoomsActivity
                val intent = Intent(this, RoomsActivity::class.java)
                startActivity(intent)
            } else {
                // Invalid credentials, show toast message
                Toast.makeText(this, "Invalid credentials. Please try again.", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
