package com.example.vehicleinsuranceapp;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class claim_status extends AppCompatActivity {

    private TextView textClaimStatus;
    private ListView listViewUserData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.claim_status);

        // Initialize views
        textClaimStatus = findViewById(R.id.text_claim_status);
        listViewUserData = findViewById(R.id.listView_user_data);

        // Get claim status from intent extras
        String claimStatus = getIntent().getStringExtra("claim_status");

        // Update claim status text
        if (claimStatus != null) {
            if (claimStatus.equals("success")) {
                textClaimStatus.setText("Successfully Claimed");
            } else if (claimStatus.equals("pending")) {
                textClaimStatus.setText("Pending");
            } else {
                textClaimStatus.setText("Unknown Status");
            }
        }

        // Fetch user data when the activity starts
        fetchUserData("user_id");
    }

    private void fetchUserData(String id) {
        String baseUrl = "https://nodejs-mongodb-cnts.onrender.com/claims/";
        String endpoint = baseUrl + id;

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(endpoint);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");

                    int responseCode = urlConnection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                        StringBuilder response = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }
                        reader.close();

                        // Parse the JSON response and populate the ListView
                        parseAndPopulateListView(response.toString());
                    } else {
                        // Handle HTTP error response
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(claim_status.this, "Failed to fetch user data", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    urlConnection.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(claim_status.this, "Failed to connect to server", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
    }

    private void parseAndPopulateListView(String responseData) {
        try {
            // Parse JSON response
            JSONArray jsonArray = new JSONArray(responseData);
            List<String> userDataList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                // Extract user data fields and format them as desired
                String userData = "Field 1: " + jsonObject.getString("field1")
                        + "\nField 2: " + jsonObject.getString("field2");
                userDataList.add(userData);
            }
            // Populate ListView with user data
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userDataList);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    listViewUserData.setAdapter(adapter);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(claim_status.this, "Failed to parse user data", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
