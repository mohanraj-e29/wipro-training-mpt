package com.example.vehicleinsuranceapp;

<<<<<<< HEAD
<<<<<<< HEAD
=======
import android.content.Intent;
>>>>>>> e5820d4 (Initial commit on appdev branch)
=======
>>>>>>> origin/appdev
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> origin/appdev
import com.example.vehicleinsuranceapp.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;

<<<<<<< HEAD
=======

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn;
    EditText name, pass;
>>>>>>> e5820d4 (Initial commit on appdev branch)
=======
>>>>>>> origin/appdev

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

<<<<<<< HEAD
<<<<<<< HEAD
    }
=======
        setContentView(R.layout.login);
        btn = findViewById(R.id.btn1);
        btn.setOnClickListener(e -> {
            String nt = name.getText().toString();
            String pt = pass.getText().toString();

            if (nt.equals("mohan") && pt.equals("123")) {
                // logged in successfully
                Toast.makeText(MainActivity.this, "Logged in Successfully", Toast.LENGTH_LONG).show();

                startActivity(new Intent(MainActivity.this, main_menu.class));// Navigate to a new Page
            } else {
                Toast.makeText(MainActivity.this, "Incorrect Credentials", Toast.LENGTH_LONG).show();
            }
        });
        name = findViewById(R.id.namebox);
        pass = findViewById(R.id.passbox);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

    }

>>>>>>> e5820d4 (Initial commit on appdev branch)
=======
    }
>>>>>>> origin/appdev
}