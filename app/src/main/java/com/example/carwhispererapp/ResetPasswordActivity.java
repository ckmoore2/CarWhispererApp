package com.example.carwhispererapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText emailEditText;
    private Button submitButton;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance();

        // Find views
        emailEditText = findViewById(R.id.forgotten_email_input);
        submitButton = findViewById(R.id.submit_btn);

        // Set onClickListener for submit button
        submitButton.setOnClickListener(view -> resetPassword());
    }

    private void resetPassword() {
        String email = emailEditText.getText().toString().trim();

        // Validate input
        if (TextUtils.isEmpty(email)) {
            emailEditText.setError("Email is required");
            emailEditText.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Please enter a valid email address");
            emailEditText.requestFocus();
            return;
        }

        // Send password reset email
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener((OnCompleteListener<Void>) task -> {
                    if (task.isSuccessful()) {
                        // Show success message
                        Toast.makeText(ResetPasswordActivity.this, "Reset email sent successfully", Toast.LENGTH_SHORT).show();
                        finish(); // Close activity after success
                    } else {
                        // Handle errors
                        handleResetPasswordError(task.getException());
                    }
                });
    }

    private void handleResetPasswordError(Exception exception) {
        if (exception != null) {
            // Specific error for invalid user
            emailEditText.setError("No account found with this email");
            emailEditText.requestFocus();
        } else {
            // Generic error message
            Toast.makeText(ResetPasswordActivity.this, "Failed to send reset email. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }
}
