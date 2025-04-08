package com.example.lab;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedpreferences.getString("username", "");
        Toast.makeText(getApplicationContext(), "Welcome " + username, Toast.LENGTH_SHORT).show();




        // CardView actions
        findViewById(R.id.cardLogOut).setOnClickListener(view -> {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.clear();
            editor.apply();
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            finish();
        });

        findViewById(R.id.cardTestSample).setOnClickListener(view ->
                startActivity(new Intent(HomeActivity.this, TestSampleActivity.class)));

        findViewById(R.id.cardAppointment).setOnClickListener(view ->
                startActivity(new Intent(HomeActivity.this, AppointmentActivity.class)));

        findViewById(R.id.cardOrderDetails).setOnClickListener(view ->
                startActivity(new Intent(HomeActivity.this, OrderDetailsActivity.class)));

        findViewById(R.id.image_button).setOnClickListener(view ->
                startActivity(new Intent(HomeActivity.this, ChatPageActivity.class)));


    }
    }

