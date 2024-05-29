package com.example.homecontrollapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import java.net.URLEncoder

class RoomControlActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_control)

        val roomName = intent.getStringExtra("ROOM_NAME") ?: "Unknown Room"
        val encodedRoomName = URLEncoder.encode(roomName, "UTF-8")
        val roomNameTextView = findViewById<TextView>(R.id.roomNameTextView)
        val lightSwitch = findViewById<Switch>(R.id.lightSwitch)
        val temperatureTextView = findViewById<TextView>(R.id.temperatureTextView)
        val humidityTextView = findViewById<TextView>(R.id.humidityTextView)
        val roomsButton = findViewById<Button>(R.id.roomsButton)

        roomNameTextView.text = roomName

        // Set up button click listener
        roomsButton.setOnClickListener {
            // Navigate to RoomsActivity
            val intent = Intent(this, RoomsActivity::class.java)
            startActivity(intent)
        }

        // Fetch and display temperature
        fetchTemperature(encodedRoomName, temperatureTextView)

        // Fetch and display humidity
        fetchHumidity(encodedRoomName, humidityTextView)

        // Fetch initial light status and set switch state
        fetchLightStatus(encodedRoomName, lightSwitch)

        // Control lights
        lightSwitch.setOnCheckedChangeListener { _, isChecked ->
            updateLightStatus(encodedRoomName, isChecked)
        }
    }

    private fun fetchTemperature(roomName: String, temperatureTextView: TextView) {
        val url = "https://homecontroll-app.onrender.com/rooms/$roomName/temperature"
        Log.d("RoomControlActivity", "Fetching temperature from URL: $url")

        val request = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                Log.d("RoomControlActivity", "Temperature response: $response")
                val temperature = response.optInt("temperature", -1)
                if (temperature != -1) {
                    temperatureTextView.text = "Temperature: $temperature°C"
                } else {
                    temperatureTextView.text = "Temperature: N/A"
                }
            },
            { error ->
                Log.e("RoomControlActivity", "Failed to fetch temperature: ${error.message}")
                Toast.makeText(this, "Failed to fetch temperature: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        )
        Volley.newRequestQueue(this).add(request)
    }

    private fun fetchHumidity(roomName: String, humidityTextView: TextView) {
        val url = "https://homecontroll-app.onrender.com/rooms/$roomName/humidity"
        Log.d("RoomControlActivity", "Fetching humidity from URL: $url")

        val request = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                Log.d("RoomControlActivity", "Humidity response: $response")
                val humidity = response.optInt("humidity", -1)
                if (humidity != -1) {
                    humidityTextView.text = "Humidity: $humidity%"
                } else {
                    humidityTextView.text = "Humidity: N/A"
                }
            },
            { error ->
                Log.e("RoomControlActivity", "Failed to fetch humidity: ${error.message}")
                Toast.makeText(this, "Failed to fetch humidity: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        )
        Volley.newRequestQueue(this).add(request)
    }

    private fun fetchLightStatus(roomName: String, lightSwitch: Switch) {
        val url = "https://homecontroll-app.onrender.com/rooms/$roomName/lights"
        Log.d("RoomControlActivity", "Fetching light status from URL: $url")

        val request = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                Log.d("RoomControlActivity", "Light status response: $response")
                val lights = response.optBoolean("lights", false)
                lightSwitch.isChecked = lights
            },
            { error ->
                Log.e("RoomControlActivity", "Failed to fetch light status: ${error.message}")
                Toast.makeText(this, "Failed to fetch light status: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        )
        Volley.newRequestQueue(this).add(request)
    }

    private fun updateLightStatus(roomName: String, lights: Boolean) {
        val url = "https://homecontroll-app.onrender.com/rooms/$roomName/lights"
        val requestBody = JSONObject().apply {
            put("lights", lights)
        }
        Log.d("RoomControlActivity", "Updating light status at URL: $url with data: $requestBody")

        val request = JsonObjectRequest(Request.Method.POST, url, requestBody,
            { response ->
                Log.d("RoomControlActivity", "Light status update response: $response")
                val message = if (lights) "Lights turned on" else "Lights turned off"
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            },
            { error ->
                Log.e("RoomControlActivity", "Failed to update light status: ${error.message}")
                Toast.makeText(this, "Failed to update light status: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        )
        Volley.newRequestQueue(this).add(request)
    }
}
