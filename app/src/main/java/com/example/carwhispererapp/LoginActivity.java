package com.example.carwhispererapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private Button signInButton, signUpButton, forgotPasswordButton;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        usernameEditText = findViewById(R.id.userName_input);
        passwordEditText = findViewById(R.id.passWord_input);
        signInButton = findViewById(R.id.signIn_btn);
        signUpButton = findViewById(R.id.signUp_btn);
        forgotPasswordButton = findViewById(R.id.forgot_btn);

        signInButton.setOnClickListener(view -> signInUser());
        signUpButton.setOnClickListener(view -> startActivity(new Intent(this, RegisterActivity.class)));
        forgotPasswordButton.setOnClickListener(view -> startActivity(new Intent(this, ResetPasswordActivity.class)));
    }

    private void signInUser() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        auth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        startActivity(new Intent(this, HomeActivity.class));
                    } else {
                        Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
