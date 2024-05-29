package com.example.vehiclehealthapp

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.MultiAutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var editTextRegistrationNumber: EditText
    private lateinit var buttonApply: Button
    private lateinit var buttonRegister: Button
    private lateinit var textViewStatus: MultiAutoCompleteTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextRegistrationNumber = findViewById(R.id.editTextRegistrationNumber)
        buttonApply = findViewById(R.id.buttonApply)
        buttonRegister = findViewById(R.id.buttonNext)
        textViewStatus = findViewById(R.id.multiAutoCompleteTextView)

        buttonApply.setOnClickListener {
            val registrationNumber = editTextRegistrationNumber.text.toString()
            val checkVehicleStatusTask = CheckVehicleStatusTask(object : CheckVehicleStatusTask.OnVehicleStatusListener {
                override fun onVehicleStatusSuccess(status: String) {
                    textViewStatus.setText(status)
                }

                override fun onVehicleStatusFailure(error: String) {
                    textViewStatus.setText(error)
                }
            })
            checkVehicleStatusTask.execute("https://nodejs-mongodb-cnts.onrender.com/checkvehicle/$registrationNumber")
        }

        buttonRegister.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }

    private class CheckVehicleStatusTask(private val listener: OnVehicleStatusListener) : AsyncTask<String, Void, String?>() {

        interface OnVehicleStatusListener {
            fun onVehicleStatusSuccess(status: String)
            fun onVehicleStatusFailure(error: String)
        }

        override fun doInBackground(vararg params: String?): String? {
            val urlString = params[0] ?: return null
            return try {
                val url = URL(urlString)
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.connect()

                val responseCode = connection.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val inputStream = connection.inputStream
                    val reader = BufferedReader(InputStreamReader(inputStream))
                    val response = StringBuilder()
                    var line: String?
                    while (reader.readLine().also { line = it } != null) {
                        response.append(line)
                    }
                    reader.close()
                    response.toString()
                } else {
                    "Error: ${connection.responseMessage}"
                }
            } catch (e: Exception) {
                "Error: ${e.message}"
            }
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            if (result != null) {
                // Log the result for debugging
                Log.d("CheckVehicleStatusTask", "Server response: $result")
                try {
                    val jsonObject = JSONObject(result)
                    if (jsonObject.getBoolean("exists")) {
                        listener.onVehicleStatusSuccess(jsonObject.getString("status"))
                    } else {
                        listener.onVehicleStatusFailure("Vehicle not found. You have to register first.")
                    }
                } catch (e: Exception) {
                    // Log the error for debugging
                    Log.e("CheckVehicleStatusTask", "Error parsing JSON: ${e.message}")
                    listener.onVehicleStatusFailure("Error parsing vehicle status. Server response: $result")
                }
            } else {
                listener.onVehicleStatusFailure("Error fetching vehicle status.")
            }
        }
    }
}
