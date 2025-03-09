package com.example.lab;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText edUsername, edEmail, edPassword, edConfirm;
    private Button btnRegister;
    private TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize views
        initializeViews();

        // Set up button listeners
        setupListeners();
    }

    private void initializeViews() {
        edUsername = findViewById(R.id.editTextAppFullName);
        edEmail = findViewById(R.id.editTextAppAddress);
        edPassword = findViewById(R.id.editTextAppContactNumber);
        edConfirm = findViewById(R.id.editTextAppFees);
        btnRegister = findViewById(R.id.buttonAppBack);
        tvLogin = findViewById(R.id.textViewExistingUser);
    }

    private void setupListeners() {
        tvLogin.setOnClickListener(view ->
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class))
        );

        btnRegister.setOnClickListener(view -> registerUser());
    }

    private void registerUser() {
        String username = edUsername.getText().toString().trim();
        String email = edEmail.getText().toString().trim();
        String password = edPassword.getText().toString();
        String confirm = edConfirm.getText().toString();

        Database db = new Database(getApplicationContext(),"lab",null,1);

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_SHORT).show();
        } else if (!password.equals(confirm)) {
            Toast.makeText(getApplicationContext(), "Password and Confirm password didn't match", Toast.LENGTH_SHORT).show();
        } else if (!isValid(password)) {
            Toast.makeText(getApplicationContext(), "Password must contain at least 8 characters, including a letter, digit, and special symbol", Toast.LENGTH_SHORT).show();
        } else {
            db.register(username, email, password);
            Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        }
    }

    public static boolean isValid(String password) {
        boolean hasLetter = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        if (password.length() < 8) return false;

        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) hasLetter = true;
            if (Character.isDigit(c)) hasDigit = true;
            if ("!@#$%^&*()_-+=<>?".contains(String.valueOf(c))) hasSpecialChar = true; // Simplified special char check
        }

        return hasLetter && hasDigit && hasSpecialChar;
    }
}
