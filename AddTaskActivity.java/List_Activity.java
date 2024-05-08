package com.example.todolistproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ListActivity extends AppCompatActivity {

    private static final String TAG = "ListActivity";
    private String email;
    private Button addTaskButton; // Renamed the button variable
    private String API_URL ;
    private TextView textViewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // Retrieve email from intent extra
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("email")) {
            email = intent.getStringExtra("email");
            API_URL = "https://nodeserver-4smk.onrender.com/tododata/" + email; // Construct API URL
        } else {
            Log.e(TAG, "No email found in intent extras");
            // Handle case where email is not passed properly
        }

        // Initialize views
        textViewData = findViewById(R.id.text_view_data);
        addTaskButton = findViewById(R.id.add_task_btn); // Changed the button ID
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click event for add task button
                Intent intent = new Intent(ListActivity.this, AddTaskActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });

        // Fetch data from API endpoint
        new FetchDataAsyncTask().execute();
        ViewGroup vg = findViewById (R.id.listactivity);
        vg.invalidate();

    }

    private class FetchDataAsyncTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            HttpURLConnection connection = null;
            StringBuilder result = new StringBuilder();

            try {
                URL url = new URL(API_URL);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                reader.close();
            } catch (IOException e) {
                Log.e(TAG, "Error fetching data: " + e.getMessage());
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }

            return result.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            // Parse JSON data and display in TextView
            try {
                JSONArray jsonArray = new JSONArray(result);
                StringBuilder data = new StringBuilder();

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String title = jsonObject.getString("title");
                    String body = jsonObject.getString("desc");
                    String time = jsonObject.getString("time");
                    data.append("Title: ").append(title).append("\nDescription: ").append(body).append("\nTime").append(time).append("\n\n");
                }

                textViewData.setText(data.toString());
            } catch (JSONException e) {
                Log.e(TAG, "Error parsing JSON: " + e.getMessage());
            }
        }
    }
}
