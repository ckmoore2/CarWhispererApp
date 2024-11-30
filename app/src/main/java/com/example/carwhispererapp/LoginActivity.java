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
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button signInButton, signUpButton, forgotPasswordButton;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance();


        // Initialize UI elements
        emailEditText = findViewById(R.id.login_username_input);
        passwordEditText = findViewById(R.id.login_password_input);
        signInButton = findViewById(R.id.login_signIn_btn);
        forgotPasswordButton = findViewById(R.id.forgot_btn);

        // Set button click listeners
        signInButton.setOnClickListener(view -> signInUser());
        signUpButton.setOnClickListener(view -> startActivity(new Intent(this, RegisterActivity.class)));
        forgotPasswordButton.setOnClickListener(view -> startActivity(new Intent(this, ResetPasswordActivity.class)));
    }

    private void signInUser() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Validate email and password
        if (!validateInputs(email, password)) return;

        // Sign in with Firebase Authentication
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Authentication successful
                        FirebaseUser user = auth.getCurrentUser();
                        if (user != null) {
                            Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(this, HomeActivity.class));
                            finish();
                        }
                    } else {
                        // Handle authentication errors
                        if (task.getException() != null) {
                            handleAuthError(task.getException().getMessage());
                        }
                    }
                });
    }

    private boolean validateInputs(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            emailEditText.setError("Email is required");
            emailEditText.requestFocus();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Invalid email format");
            emailEditText.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("Password is required");
            passwordEditText.requestFocus();
            return false;
        }

        if (!password.matches("^(?=.*[0-9])(?=.*[a-zA-Z]).{6,}$")) {
            passwordEditText.setError("Password must be at least 6 characters, include a letter and a number");
            passwordEditText.requestFocus();
            return false;
        }

        return true;
    }

    private void handleAuthError(String errorCode) {
        if (errorCode == null) {
            Toast.makeText(this, "Authentication failed. Try again.", Toast.LENGTH_SHORT).show();
            return;
        }

        switch (errorCode) {
            case "ERROR_INVALID_EMAIL":
                emailEditText.setError("Invalid email format");
                emailEditText.requestFocus();
                break;

            case "ERROR_WRONG_PASSWORD":
                passwordEditText.setError("Incorrect password");
                passwordEditText.requestFocus();
                break;

            case "ERROR_USER_NOT_FOUND":
                emailEditText.setError("No account with this email found");
                emailEditText.requestFocus();
                break;

            default:
                Toast.makeText(this, "Authentication failed. Try again.", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
