package com.example.lab;

import static com.basgeekball.awesomevalidation.ValidationStyle.COLORATION;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

public class TestSampleBookActivity extends AppCompatActivity {

    EditText edname, edaddress, edcontact, edpincode;
    Button btnBooking;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_sample_book);

        // Use class-level instance instead of redeclaring it inside onCreate
        awesomeValidation = new AwesomeValidation(COLORATION);
        awesomeValidation.setColor(Color.RED);

        edname = findViewById(R.id.editTextLTBFullName);
        edaddress = findViewById(R.id.editTextLTBAddress);
        edcontact = findViewById(R.id.editTextLTBContact);
        edpincode = findViewById(R.id.editTextLTBPincode);
        btnBooking = findViewById(R.id.buttonLTBBooking);

        // Validation rules
        awesomeValidation.addValidation(this, R.id.editTextLTBFullName, "[a-zA-Z\\s]+", R.string.err_name);
        awesomeValidation.addValidation(this, R.id.editTextLTBContact, RegexTemplate.TELEPHONE, R.string.err_tel); // Fixed this line
        awesomeValidation.addValidation(this, R.id.editTextLTBAddress, "^[A-Za-z0-9 ,.-]{5,}$", R.string.err_add);
        awesomeValidation.addValidation(this, R.id.editTextLTBPincode, "^[1-9][0-9]{5}$", R.string.err_pin);

        // Retrieve data from Intent
        Intent intent = getIntent();
        String priceString = intent.getStringExtra("price");
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");

        if (priceString != null) {
            String[] price = priceString.split(java.util.regex.Pattern.quote(":"));

            btnBooking.setOnClickListener(v -> {
                if (awesomeValidation.validate()) {
                    String aa = edname.getText().toString();
                    String bb = edaddress.getText().toString();
                    String cc = edcontact.getText().toString();
                    String dd = edpincode.getText().toString();

                    Toast.makeText(getApplicationContext(), "Your booking is done successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(TestSampleBookActivity.this, HomeActivity.class));

                    // Get shared preferences
                    SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                    String username = sharedpreferences.getString("username", "");

                    // Insert order into database
                    Database db = new Database(getApplicationContext(), "labtestease", null, 1);
                    db.addOrder(username, aa, bb, cc, Integer.parseInt(dd), date, time, Float.parseFloat(price[1]), "sample");
                    db.removeCart(username, "sample");
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid Filling", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Error retrieving  information", Toast.LENGTH_SHORT).show();
        }
    }
}
