package com.carcar.telemedicine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class profileactivity extends AppCompatActivity {

    LinearLayout l1, l2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profileactivity);

        // Set status bar color
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.neon_violate));

        // Initialize views
        l1 = findViewById(R.id.doctorProfile);
        l2 = findViewById(R.id.patientProfile);


        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Set click listeners
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.putString("profileType", "Doctor Profile"); // Store profile type
                editor.apply(); // Save changes

                Intent intent = new Intent(profileactivity.this, loginpage.class);
                intent.putExtra("profileType", "Doctor Profile");
                startActivity(intent);
            }
        });

        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.putString("profileType", "Patient Profile"); // Store profile type
                editor.apply(); // Save changes

                Intent intent = new Intent(profileactivity.this, loginpage.class);
                intent.putExtra("profileType", "Patient Profile");
                startActivity(intent);
            }
        });
    }
}
