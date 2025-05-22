package com.carcar.telemedicine;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.biometric.BiometricManager;

import java.util.concurrent.Executor;

public class loginpage extends AppCompatActivity {

    TextView uname, pass,profile;
    Button btn, signup;
    CheckBox showbutton;
    ImageView biometric,pic;

    LoginDao l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_loginpage);

        // Set Status Bar Color
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.neon_violate));

        // Initialize UI Components
        uname = findViewById(R.id.Uname);
        pass = findViewById(R.id.Passwrod);
        biometric = findViewById(R.id.biometric);
        btn = findViewById(R.id.btn);
        signup = findViewById(R.id.btnSignUp);
        showbutton = findViewById(R.id.checkbox);
        profile=findViewById(R.id.profile);
        pic=findViewById(R.id.gif);



        String profileType = getIntent().getStringExtra("profileType");

        // Set the profile type to TextView
        if (profileType != null) {
            profile.setText(profileType);
        }
        if ("Doctor Profile".equals(profileType)) {
            pic.setImageResource(R.drawable.doctor);
        } else if ("Patient Profile".equals(profileType)) {
            pic.setImageResource(R.drawable.patient);
        }

        // Initialize Database
        UserDatabase db = UserDatabase.getInstance(this);
        l = db.loginDao();

        // Insert Default Users (if not exists)
        insertUser("Doctor1", "KARTHIK");
        insertUser("Doctor2", "MOUNI");
        insertUser("Doctor3", "CHARAN");

        // Show/Hide Password Toggle
        showbutton.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });

        // Login Button Click
        btn.setOnClickListener(View -> {
            String name = uname.getText().toString().trim();
            String password = pass.getText().toString().trim();

            if (name.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            if (l.validateuser(name, password) > 0) {
                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();

                // Navigate to Main Activity
                Intent toInto = new Intent(this, introactivity.class);
                startActivity(toInto);
                finishAffinity(); // Close all previous activities

            } else {
                Toast.makeText(this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
            }
        });

        // Signup Button Click
        signup.setOnClickListener(View -> {
            Intent toSignup = new Intent(this, signupactivity.class);
            toSignup.putExtra("profileType1", profileType);
            startActivity(toSignup);
        });

        // Biometric Authentication
        biometric.setOnClickListener(View -> {
            checkBiometricSupport();
            showBiometricPrompt();
        });
    }

    private void insertUser(String reg, String pass) {
        if (l.validateuser(reg, pass) == 0) {
            l.insert(new LoginStruct(reg, pass));
        }
    }

    private void checkBiometricSupport() {
        BiometricManager biometricManager = BiometricManager.from(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            switch (biometricManager.canAuthenticate()) {
                case BiometricManager.BIOMETRIC_SUCCESS:
                    break;
                case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                    Toast.makeText(this, "No biometric hardware detected", Toast.LENGTH_SHORT).show();
                    break;
                case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                    Toast.makeText(this, "Biometric hardware is currently unavailable", Toast.LENGTH_SHORT).show();
                    break;
                case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                    Toast.makeText(this, "No biometric data enrolled. Please set up biometrics.", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }

    private void showBiometricPrompt() {
        Executor executor = ContextCompat.getMainExecutor(this);
        BiometricPrompt biometricPrompt = new BiometricPrompt(this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(getApplicationContext(), "Authentication Error: " + errString, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);

                // Redirect to Main Activity
                Intent intent = new Intent(loginpage.this, introactivity.class);
                startActivity(intent);
                finishAffinity();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getApplicationContext(), "Authentication Failed", Toast.LENGTH_SHORT).show();
            }
        });

        BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("SCHEDULO")
                .setSubtitle("Login using your biometric credential")
                .setNegativeButtonText("Use account password")
                .build();

        biometricPrompt.authenticate(promptInfo);
    }
}
