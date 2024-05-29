package com.example.vehicleinsuranceapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class message extends AppCompatActivity {

    private ListView listViewMessages;
    private Button buttonPrint;
    private Button buttonBack;
    private final String CLAIMS_ENDPOINT_URL = "https://nodejs-mongodb-cnts.onrender.com/claims"; // Replace with your actual endpoint URL

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_thread);

        // Initialize views
        listViewMessages = findViewById(R.id.listView_messages);
        buttonPrint = findViewById(R.id.button_send_message);
        buttonBack = findViewById(R.id.button3);

        // Set click listener for the Print button
        buttonPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchClaimsData();
            }
        });

        // Set click listener for the Back button
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the main menu
                Intent intent = new Intent(message.this, main_menu.class);
                startActivity(intent);
            }
        });
    }

    private void fetchClaimsData() {
        new FetchClaimsTask().execute();
    }

    private class FetchClaimsTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String claimsData = null;

            try {
                URL url = new URL(CLAIMS_ENDPOINT_URL);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuilder builder = new StringBuilder();
                if (inputStream == null) {
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line).append("\n");
                }
                if (builder.length() == 0) {
                    return null;
                }
                claimsData = builder.toString();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return claimsData;
        }

        @Override
        protected void onPostExecute(String responseData) {
            if (responseData != null) {
                populateListViewWithClaims(responseData);
            } else {
                Toast.makeText(message.this, "Failed to fetch claims data", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void populateListViewWithClaims(String responseData) {
        try {
            // Parse JSON response
            JSONArray jsonArray = new JSONArray(responseData);
            List<String> claimsList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                // Extract claim details from JSON
                String vehicleModel = jsonObject.getString("vehicleModel");
                String damageDescription = jsonObject.getString("damageDescription");
                String timestampStr = jsonObject.getString("timestamp");
                // Convert timestamp string to Date
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                Date timestamp = dateFormat.parse(timestampStr);
                // Format date to display
                DateFormat dateFormatDisplay = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String timestampDisplay = dateFormatDisplay.format(timestamp);
                // Construct claim info string
                String claimInfo = "Vehicle Model: " + vehicleModel + "\nDamage Description: " + damageDescription + "\nTimestamp: " + timestampDisplay;
                claimsList.add(claimInfo);
            }
            // Populate ListView with claims data
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, claimsList);
            listViewMessages.setAdapter(adapter);
        } catch (JSONException | ParseException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to parse claims data", Toast.LENGTH_SHORT).show();
        }
    }
}
