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
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    private static final String API_URL = "https://nodeserver-4smk.onrender.com/createuser"; // Replace with your API endpoint

    private EditText nameEditText, emailEditText, passwordEditText;
    private Button registerButton,loginUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameEditText = findViewById(R.id.name_edittext);
        emailEditText = findViewById(R.id.email_edittext);
        passwordEditText = findViewById(R.id.password_edittext);
        registerButton = findViewById(R.id.register_button);
        loginUser=findViewById(R.id.login_btn);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
        loginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void registerUser() {
        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // Create JSON object with user data
        JSONObject userData = new JSONObject();
        try {
            userData.put("name", name);
            userData.put("email", email);
            userData.put("password", password);
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
                Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

            } else {
                // Registration failed
                Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();

            }
           // Finish the current activity to prevent returning to it when back button is pressed
        }
    }
}
