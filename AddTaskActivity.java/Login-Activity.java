package com.example.todolistproject;

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
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final String API_URL = "https://nodeserver-4smk.onrender.com/login";

    private EditText emailEditText, passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.email_edittext);
        passwordEditText = findViewById(R.id.password_edittext);
        loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        JSONObject userData = new JSONObject();
        try {
            userData.put("email", email);
            userData.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        new LoginAsyncTask().execute(userData.toString());
    }

    private class LoginAsyncTask extends AsyncTask<String, Void, String> {

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

                BufferedOutputStream outputStream = new BufferedOutputStream(connection.getOutputStream());
                outputStream.write(jsonData.getBytes());
                outputStream.flush();
                outputStream.close();

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


//        protected void onPostExecute(String result) {
//            if (!result.isEmpty()) {
//                // Login successful
//                Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(LoginActivity.this, ListActivity.class);
//                intent.putExtra("email", emailEditText.getText().toString());
//                startActivity(intent);
//                finish(); // finish LoginActivity to prevent returning to it
//            } else {
//                // Login failed
//                Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
//            }
//        }
        @Override
        protected void onPostExecute(String result) {
            try {
                Toast.makeText(LoginActivity.this, result, Toast.LENGTH_LONG).show();
                char[] res=result.toCharArray();
                char r=res[11];
                if(r=='t'){
                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();

                        // Retrieve email from response if available


                            // Start ListActivity and pass email as extra
                         Intent intent = new Intent(LoginActivity.this, ListActivity.class);
                         intent.putExtra("email", emailEditText.getText().toString());
                         startActivity(intent);
                        finish();
                }
                else{
                    Toast.makeText(LoginActivity.this, "Invalid username or Password", Toast.LENGTH_SHORT).show();
                }
//                // Check if result is empty
//                if (result != null && !result.isEmpty()) {
//                    // Try parsing the result as JSON
//                    JSONObject jsonResponse = new JSONObject(result);
//
//                    // Check if the response contains the 'success' field
//                    if (jsonResponse.has("success")) {
//                        boolean success = jsonResponse.getBoolean("success");
//                        if (success) {
//                            // Login successful
//                            Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
//
//                            // Retrieve email from response if available
//                            String email = jsonResponse.optString("email", "");
//
//                            // Start ListActivity and pass email as extra
//                            Intent intent = new Intent(LoginActivity.this, ListActivity.class);
//                            intent.putExtra("email", email);
//                            startActivity(intent);
//
//                            // Finish LoginActivity to prevent returning to it
//

            } catch (Exception e) {
                // JSON parsing error
                Log.e(TAG, "Error parsing JSON response: " + e.getMessage());
                Toast.makeText(LoginActivity.this, "Error parsing JSON response", Toast.LENGTH_SHORT).show();
            }

        }

    }
}
