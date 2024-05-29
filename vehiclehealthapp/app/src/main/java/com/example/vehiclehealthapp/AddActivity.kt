package com.example.vehiclehealthapp

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val editTextRegistrationNumber = findViewById<EditText>(R.id.editTextRegistrationNumber)
        val editTextOwnerName = findViewById<EditText>(R.id.editTextOwnerName)
        val editTextVehicleType = findViewById<EditText>(R.id.editTextVehicleType)
        val editTextMileage = findViewById<EditText>(R.id.editTextMileage)
        val editTextStatus = findViewById<EditText>(R.id.editTextStatus)
        val buttonSubmit = findViewById<Button>(R.id.buttonSubmit)
        val buttonBack = findViewById<Button>(R.id.buttonback)

        buttonSubmit.setOnClickListener {
            val registrationNumber = editTextRegistrationNumber.text.toString()
            val ownerName = editTextOwnerName.text.toString()
            val vehicleType = editTextVehicleType.text.toString()
            val mileage = editTextMileage.text.toString()
            val status = editTextStatus.text.toString()

            // URL of the endpoint to add a vehicle
            val addVehicleUrl = "https://nodejs-mongodb-cnts.onrender.com/addvehicle"

            // Create an instance of AddVehicleAsyncTask
            val addVehicleTask = AddVehicleAsyncTask(object : AddVehicleAsyncTask.OnAddVehicleListener {
                override fun onAddVehicleSuccess(result: String) {
                    // Show success toast message
                    runOnUiThread {
                        Toast.makeText(this@AddActivity, "Registered Successfully", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onAddVehicleFailure(error: String) {
                    // Show failure toast message
                    runOnUiThread {
                        Toast.makeText(this@AddActivity, "Error: $error", Toast.LENGTH_SHORT).show()
                    }
                }
            })

            // Execute the AsyncTask passing the URL and vehicle details
            addVehicleTask.execute(addVehicleUrl, registrationNumber, ownerName, vehicleType, mileage, status)
        }

        buttonBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private class AddVehicleAsyncTask(private val listener: OnAddVehicleListener) : AsyncTask<String, Void, String>() {

        interface OnAddVehicleListener {
            fun onAddVehicleSuccess(result: String)
            fun onAddVehicleFailure(error: String)
        }

        override fun doInBackground(vararg params: String?): String {
            val urlString = params[0] // Endpoint URL

            return try {
                val url = URL(urlString)
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "POST"
                connection.doOutput = true
                connection.setRequestProperty("Content-Type", "application/json")

                val jsonObject = JSONObject().apply {
                    put("registrationNumber", params[1])
                    put("ownerName", params[2])
                    put("vehicleType", params[3])
                    put("mileage", params[4])
                    put("status", params[5])
                }

                val outputStream = DataOutputStream(connection.outputStream)
                outputStream.writeBytes(jsonObject.toString())
                outputStream.flush()
                outputStream.close()

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

        override fun onPostExecute(result: String) {
            if (result.startsWith("Error")) {
                listener.onAddVehicleFailure(result)
            } else {
                listener.onAddVehicleSuccess(result)
            }
        }
    }
}
