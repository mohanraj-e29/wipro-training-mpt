package com.example.vehicleinsuranceapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class main_menu extends AppCompatActivity implements View.OnClickListener {

    private Button button_file_claim, button_check_status, button_communicate, button_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        // Find buttons by their IDs
        button_file_claim = findViewById(R.id.button_file_claim);
        button_check_status = findViewById(R.id.button_check_status);
        button_communicate = findViewById(R.id.button_communicate);
        button_logout = findViewById(R.id.button_logout);

        // Set click listeners for buttons
        button_file_claim.setOnClickListener(this);
        button_check_status.setOnClickListener(this);
        button_communicate.setOnClickListener(this);
        button_logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (v == button_file_claim) {
            // Redirect to the Register class
            intent = new Intent(main_menu.this, register.class);
        } else if (v == button_check_status) {
            // Redirect to the ClaimStatus class
            intent = new Intent(main_menu.this, claim_status.class);
        } else if (v == button_communicate) {
            // Redirect to the Message class
            intent = new Intent(main_menu.this, message.class);
        } else if (v == button_logout) {
            // Redirect to the MainActivity
            intent = new Intent(main_menu.this, MainActivity.class);
            finish(); // Close the current activity
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}
