package com.carcar.telemedicine;

import android.os.Bundle;
import android.view.Window;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;
import java.util.Map;

public class SmartMedicineActivity extends AppCompatActivity {

    private EditText symptomsInput;
    private Button suggestButton;
    private TextView resultView;

    private Map<String, String> medicineDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_smart_medicine);
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.neon_violate));


        symptomsInput = findViewById(R.id.symptomsInput);
        suggestButton = findViewById(R.id.suggestButton);
        resultView = findViewById(R.id.resultView);

        // Sample medicine database (can be expanded)
        medicineDatabase = new HashMap<>();
        medicineDatabase.put("fever", "Paracetamol");
        medicineDatabase.put("cold", "Cetirizine");
        medicineDatabase.put("headache", "Ibuprofen");
        medicineDatabase.put("cough", "Dextromethorphan");
        medicineDatabase.put("sore throat", "Lozenges / Azithromycin");
        medicineDatabase.put("body pain", "Diclofenac / Naproxen");
        medicineDatabase.put("stomach pain", "Dicyclomine / Pantoprazole");
        medicineDatabase.put("indigestion", "Domperidone / Ranitidine");
        medicineDatabase.put("vomiting", "Ondansetron");
        medicineDatabase.put("diarrhea", "ORS / Loperamide");
        medicineDatabase.put("constipation", "Lactulose / Bisacodyl");
        medicineDatabase.put("allergy", "Levocetirizine / Fexofenadine");
        medicineDatabase.put("skin rash", "Hydrocortisone / Calamine");
        medicineDatabase.put("acidity", "Omeprazole / Pantoprazole");
        medicineDatabase.put("high blood pressure", "Amlodipine / Metoprolol");
        medicineDatabase.put("diabetes", "Metformin / Glimepiride");
        medicineDatabase.put("asthma", "Salbutamol / Budesonide Inhaler");
        medicineDatabase.put("arthritis", "Ibuprofen / Naproxen");
        medicineDatabase.put("muscle cramps", "Magnesium / Potassium Supplements");
        medicineDatabase.put("migraine", "Sumatriptan / Rizatriptan");
        medicineDatabase.put("anemia", "Iron Supplements / Folic Acid");
        medicineDatabase.put("insomnia", "Melatonin / Diazepam");
        medicineDatabase.put("anxiety", "Alprazolam / Clonazepam");
        medicineDatabase.put("depression", "Sertraline / Fluoxetine");



        suggestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suggestMedicine();
            }
        });
    }

    private void suggestMedicine() {
        String input = symptomsInput.getText().toString().trim().toLowerCase();
        if (input.isEmpty()) {
            resultView.setText("Please enter symptoms.");
            return;
        }

        String suggestedMedicine = suggestMedicine(input); // Get suggested medicine

        if (suggestedMedicine != null) {
            resultView.setText("Suggested Medicine: " + suggestedMedicine);
        } else {
            resultView.setText("No medicine found for the given symptoms. Please consult a doctor.");
        }
    }

    public String suggestMedicine(String userInput) {
        // Convert input text to lowercase to handle case-insensitivity
        userInput = userInput.toLowerCase();

        // Check each symptom in the database
        for (String symptom : medicineDatabase.keySet()) {
            if (userInput.contains(symptom)) { // If symptom found in user input
                return medicineDatabase.get(symptom); // Return only the medicine name
            }
        }
        return null; // Return null if no match is found
    }

}