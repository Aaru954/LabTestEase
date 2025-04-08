package com.example.lab;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AppointmentActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        ImageButton youtubeButton = findViewById(R.id.youtubeButton);
        youtubeButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=libk2qqcEA8"));
            startActivity(intent);
        });


        CardView exit = findViewById(R.id.cardBack);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AppointmentActivity.this, HomeActivity.class));
            }
        });






        CardView ThyrocareTechnologies = findViewById(R.id.cardThyrocareTechnologies);
        ThyrocareTechnologies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(AppointmentActivity.this, LabDetailsActivity.class);
                it.putExtra("title", "ThyrocareTechnologies");
                startActivity(it);

            }
        });




        CardView ApolloDiagnostics = findViewById(R.id.cardApolloDiagnostics);
        ApolloDiagnostics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(AppointmentActivity.this, LabDetailsActivity.class);
                it.putExtra("title", "ApolloDiagnostics");
                startActivity(it);

            }
        });
        CardView MetropolisHealthcare = findViewById(R.id.cardMetropolisHealthcare);
        MetropolisHealthcare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(AppointmentActivity.this, LabDetailsActivity.class);
                it.putExtra("title", "MetropolisHealthcare");
                startActivity(it);
            }
        });

        CardView DrLalPathLabs = findViewById(R.id.cardDrLalPathLabs);
        DrLalPathLabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(AppointmentActivity.this, LabDetailsActivity.class);
                it.putExtra("title", "DrLalPathLabs");
                startActivity(it);

            }
        });
        CardView SRLDiagnostics = findViewById(R.id.cardSRLDiagnostics);
        SRLDiagnostics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(AppointmentActivity.this, LabDetailsActivity.class);
                it.putExtra("title", "SRLDiagnostics");
                startActivity(it);

            }

        });


    }

}