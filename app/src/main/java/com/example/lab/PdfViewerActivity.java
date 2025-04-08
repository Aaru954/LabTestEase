package com.example.lab;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class PdfViewerActivity extends AppCompatActivity {

    private String pdfPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);

        // Toolbar setup
        Toolbar toolbar = findViewById(R.id.pdfToolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        // PDF path from intent
        pdfPath = getIntent().getStringExtra("pdf_path");

        PDFView pdfView = findViewById(R.id.pdfView);
        ProgressBar loadingIndicator = findViewById(R.id.loadingIndicator);

        if (pdfPath != null) {
            File file = new File(pdfPath);
            if (file.exists()) {
                loadingIndicator.setVisibility(View.VISIBLE);

                pdfView.fromFile(file)
                        .enableSwipe(true)
                        .swipeHorizontal(false)
                        .enableDoubletap(true)
                        .enableAnnotationRendering(true)
                        .spacing(10)
                        .onLoad(nbPages -> loadingIndicator.setVisibility(View.GONE)) // Hide loader when done
                        .load();
            } else {
                Toast.makeText(this, "PDF file does not exist", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No PDF path received", Toast.LENGTH_SHORT).show();
        }
    }
}
