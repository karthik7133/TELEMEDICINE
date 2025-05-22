package com.carcar.telemedicine;


import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Random;

public class AIDoctorMatchingActivity extends AppCompatActivity {

    private EditText symptomsInput;
    private Button findDoctorButton;
    private TextView resultText;
    private String[] doctorList = {
            "Dr. Ravi Sharma - General Physician",
            "Dr. Ananya Verma - Cardiologist",
            "Dr. Arjun Patel - Neurologist",
            "Dr. Meera Kapoor - Dermatologist",
            "Dr. Karthik Reddy - Orthopedic Surgeon",
            "Dr. Sanya Iyer - Pediatrician",
            "Dr. Vikram Khanna - Endocrinologist",
            "Dr. Priya Sen - Gynecologist",
            "Dr. Rajesh Malhotra - Gastroenterologist",
            "Dr. Neha Bansal - Pulmonologist",
            "Dr. Suresh Nair - Oncologist",
            "Dr. Alok Jain - Nephrologist",
            "Dr. Kavita Sinha - Rheumatologist",
            "Dr. Rohan Das - ENT Specialist",
            "Dr. Aditi Mehta - Ophthalmologist",
            "Dr. Sameer Roy - Psychiatrist",
            "Dr. Divya Menon - Infectious Disease Specialist",
            "Dr. Anil Gupta - Urologist",
            "Dr. Swati Desai - Allergist",
            "Dr. Manish Kapoor - Plastic Surgeon",
            "Dr. Sneha Nambiar - Geriatrician",
            "Dr. Arvind Saxena - Sports Medicine Specialist",
            "Dr. Nisha Agrawal - Pain Management Specialist"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidoctor_matching);
        // Set Status Bar Color
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.neon_violate));

        symptomsInput = findViewById(R.id.symptomsInput);
        findDoctorButton = findViewById(R.id.findDoctorButton);
        resultText = findViewById(R.id.resultText);

        findDoctorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suggestDoctor();
            }
        });
    }

    private void suggestDoctor() {
        String symptoms = symptomsInput.getText().toString().trim();
        if (!symptoms.isEmpty()) {
            Random random = new Random();
            String matchedDoctor = doctorList[random.nextInt(doctorList.length)];
            resultText.setText("Based on your symptoms, we recommend:\n" + matchedDoctor);
        } else {
            resultText.setText("Please enter your symptoms for AI to match a doctor.");
        }
    }
}
