package com.example.;

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

public class todo_page extends AppCompatActivity {

    private static final String TAG = "AddTaskActivity";
    private String email;
    private String API_URL; // API URL variable

    private EditText titleEditText,descEditText,timeEditText;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.todo_page);

        // Retrieve email from intent extra
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("email")) {
            email = intent.getStringExtra("email");
            API_URL = "https://nodeserver-4smk.onrender.com/createtodo/" + email; // Construct API URL
        } else {
            Log.e(TAG, "No email found in intent extras");
            // Handle case where email is not passed properly
        }

        titleEditText = findViewById(R.id.title_tv);
        descEditText = findViewById(R.id.desc_tv);
        timeEditText = findViewById(R.id.time_tv);

        addButton = findViewById(R.id.add_tv);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String title= titleEditText.getText().toString();
        String desc = descEditText.getText().toString();
        String time = timeEditText.getText().toString();

        // Create JSON object with user data
        JSONObject userData = new JSONObject();
        try {
            userData.put("title", title);
            userData.put("desc", desc);
            userData.put("time", time);
            userData.put("email", email);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Send POST request to API endpoint
        new RegisterAsyncTask().execute(userData.toString());
    }

    private class RegisterAsyncTask extends AsyncTask<String, Void, String> {

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
                // Registration successful
                Toast.makeText(AddTaskActivity.this, "Task added successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddTaskActivity.this, ListActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
            } else {
                // Registration failed
                Toast.makeText(AddTaskActivity.this, "Task failed to add", Toast.LENGTH_SHORT).show();
            }
            Intent intent = new Intent(AddTaskActivity.this, ListActivity.class);
            startActivity(intent);
            finish(); // Finish the current activity to prevent returning to it when back button is pressed
        }
    }
}
