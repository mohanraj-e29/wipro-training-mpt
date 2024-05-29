package com.example.vehicleinsuranceapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class register extends AppCompatActivity {

    private static final String TAG = "register";
    private String API_URL = "https://nodejs-mongodb-cnts.onrender.com/claim"; // API URL variable

    private EditText editTextVehicleModel;
    private EditText editTextDamageDescription;
    private Button buttonSubmitClaim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.claim_form);

        // Initialize views
        editTextVehicleModel = findViewById(R.id.editText_vehicle_model);
        editTextDamageDescription = findViewById(R.id.editText_damage_description);
        buttonSubmitClaim = findViewById(R.id.button_submit_claim);

        // Set click listener for the submit claim button
        buttonSubmitClaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitClaim();
            }
        });
    }

    private void submitClaim() {
        // Retrieve text from EditText fields
        String vehicleModel = editTextVehicleModel.getText().toString();
        String damageDescription = editTextDamageDescription.getText().toString();

        // Get the current date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());

        // Create JSON object with claim data
        JSONObject claimData = new JSONObject();
        try {
            claimData.put("vehicleModel", vehicleModel); // Insert vehicle model data
            claimData.put("damageDescription", damageDescription); // Insert damage description data
            claimData.put("date", currentDateandTime); // Insert date data
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Send POST request to API endpoint
        new SubmitClaimAsyncTask().execute(claimData.toString());
    }

    private class SubmitClaimAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String jsonData = params[0];
            HttpURLConnection connection = null;
            StringBuilder response = new StringBuilder();

            try {
                URL url = new URL(API_URL);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                // Write JSON data to output stream
                BufferedOutputStream outputStream = new BufferedOutputStream(connection.getOutputStream());
                outputStream.write(jsonData.getBytes());
                outputStream.flush();
                outputStream.close();

                // Read response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
            } catch (IOException e) {
                Log.e(TAG, "Error sending POST request: " + e.getMessage());
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }

            return response.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            if (!result.isEmpty()) {
                // Claim submitted successfully
                Toast.makeText(register.this, "Claim submitted successfully", Toast.LENGTH_SHORT).show();
                // Redirect to home_page activity
                Intent intent = new Intent(register.this, claim_status.class);
                startActivity(intent);
                // Finish the current activity
                finish();
            } else {
                // Claim failed to submit
                Toast.makeText(register.this, "Failed to submit claim", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
