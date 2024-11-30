package com.example.carwhispererapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText nameEditText, emailEditText, usernameEditText, passwordEditText;
    private Button registerButton;

    private FirebaseAuth auth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize Firebase Auth and Database Reference
        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        // Find views
        nameEditText = findViewById(R.id.register_name_input);
        emailEditText = findViewById(R.id.register_email_input);
        usernameEditText = findViewById(R.id.register_username_input);
        passwordEditText = findViewById(R.id.register_password_input);
        registerButton = findViewById(R.id.register_btn);

        // Set onClickListener for register button
        registerButton.setOnClickListener(view -> registerUser());
    }

    private void registerUser() {
        String name = nameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Validate input
        if (TextUtils.isEmpty(name)) {
            nameEditText.setError("Name is required");
            nameEditText.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Valid email is required");
            emailEditText.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(username)) {
            usernameEditText.setError("Username is required");
            usernameEditText.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password) || password.length() < 6) {
            passwordEditText.setError("Password must be at least 6 characters");
            passwordEditText.requestFocus();
            return;
        }

        // Register user with Firebase Authentication
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Get the newly created user
                        FirebaseUser firebaseUser = auth.getCurrentUser();
                        if (firebaseUser != null) {
                            // Save additional user data in Firebase Realtime Database
                            saveUserData(firebaseUser.getUid(), name, email, username);
                        }
                    } else {
                        // Handle registration errors
                        String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                        handleAuthError(errorCode);
                    }
                });
    }

    private void saveUserData(String userId, String name, String email, String username) {
        // Create user data map
        Map<String, String> userMap = new HashMap<>();
        userMap.put("name", name);
        userMap.put("email", email);
        userMap.put("username", username);

        // Save data in Firebase Realtime Database
        databaseReference.child(userId).setValue(userMap)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                        // Redirect to HomeActivity or LoginActivity after successful registration
                        startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Failed to save user data. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void handleAuthError(String errorCode) {
        switch (errorCode) {
            case "ERROR_EMAIL_ALREADY_IN_USE":
                emailEditText.setError("This email is already in use");
                emailEditText.requestFocus();
                break;

            case "ERROR_INVALID_EMAIL":
                emailEditText.setError("The email address is badly formatted");
                emailEditText.requestFocus();
                break;

            case "ERROR_WEAK_PASSWORD":
                passwordEditText.setError("The password is too weak");
                passwordEditText.requestFocus();
                break;

            default:
                Toast.makeText(this, "Registration failed. Try again.", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

