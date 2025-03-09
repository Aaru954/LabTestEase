package com.example.lab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class TestSampleActivity extends AppCompatActivity {

    private String[][] packages =
            {
                    {"Package 1 : CBC (Complete Blood Count) Test", "", "", "", "999"},
                    {"Package 2 : Urine Routine And Microscopy Test", "", "", "", "299"},
                    {"Package 3 : Kidney(Renal) Test", "", "", "", "899"},
                    {"Package 4 : Fasting Blood Sugar (FBS) Test", "", "", "", "499"},
                    {"Package 5 : Total Thyroid Test", "", "", "", "699"}
            };

    private String[] package_details = {
            "CBC (Complete Blood Count) Test\n" +
                    "AEC Test\n" +
                    "Platelet Count Test\n" +
                    "Nucleated Red Blood Cells Percentage\n" +
                    "Total Leucocytes / WBC Count (TLC) Test\n" +
                    "Hemoglobin (Hb) Test\n" +
                    "Absolute Basophils Count (ABC) Test",
            "Urine Routine And Microscopy Test\n" +
                    "pH-value\n" +
                    "Urine Protein Test\n" +
                    "Urine Glucose\n" +
                    "Bacteria\n" +
                    "Urine Blood\n" +
                    "Colour",
            "Kidney(Renal) Test\n" +
                    "Blood Urea Nitrogen (BUN)/Serum Urea Test\n" +
                    "Creatinine Test\n" +
                    "Uric Acid Test\n" +
                    "Calcium (Ca) Test\n" +
                    "BUN/Creatinine Ratio\n" +
                    "Urea (Calculated)",
            "Fasting Blood Sugar (FBS) Test\n" +
                    "Average Blood Glucose\n" +
                    "Vitamin D Total\n" +
                    "HbA1c (Glycosylated Hemoglobin) Test\n" +
                    "Vitamin B12 Test",
            "Total Thyroid Test\n" +
                    "Triiodothyronine (T3) Test\n" +
                    "Thyroxine (T4) Test\n" +
                    "Thyroid Stimulating Hormone - Ultrasensitive (UTSH)"
    };

    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button btnGoToCart, btnBack;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_sample);

        btnGoToCart = findViewById(R.id.buttonCartCheckout);
        btnBack = findViewById(R.id.buttonCartBack);
        listView = findViewById(R.id.listViewCart);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TestSampleActivity.this, HomeActivity.class));
            }

        });

        list = new ArrayList();
        for (int i = 0; i < packages.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", "Total Cost:" + packages[i][4] + "/-");
            list.add(item);
        }

        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
        listView.setAdapter(sa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(TestSampleActivity.this,TestSampleDetailsActivity.class);
                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",package_details[i]);
                it.putExtra("text3",packages[i][4]);
                startActivity(it);

            }
        });

        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TestSampleActivity.this,CartSampleActivity.class));
            }
        });
    }
}

