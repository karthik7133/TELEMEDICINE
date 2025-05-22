package com.carcar.telemedicine;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class introactivity extends AppCompatActivity {

    CardView c1,c2,c3,c4,c5,c6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_introactivity);
        // Set Status Bar Color
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.neon_violate));

        try {
            CardView cardView=findViewById(R.id.scheduler);
            GradientDrawable gradientDrawable = new GradientDrawable(
                    GradientDrawable.Orientation.BL_TR,
                    new int[]{ Color.parseColor("#00008B"), Color.parseColor("#000000") }
                    // At least two colors
            );

            gradientDrawable.setCornerRadius(24f);
            cardView.setBackground(gradientDrawable);



        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        c1=findViewById(R.id.patientchat);
        c2=findViewById(R.id.vcallchat);
        c3=findViewById(R.id.medicalstore);
        c4=findViewById(R.id.medicine);
        c5=findViewById(R.id.community);
        c6=findViewById(R.id.ai);

        c1.setOnClickListener(View->{
            Intent tochat=new Intent(this, ChatActivity.class);
            startActivity(tochat);
        });

        c2.setOnClickListener(View->{
            Intent tovcall=new Intent(this,DoctorChatActivity.class);
            startActivity(tovcall);
        });
        c3.setOnClickListener(View->{
            Intent tonear=new Intent(this,NearbyMedicalShopsActivity.class);
            startActivity(tonear);
        });
        c4.setOnClickListener(View->{
            Intent tomedicalsug =new Intent(this, SmartMedicineActivity.class);
            startActivity(tomedicalsug);
        });
        c5.setOnClickListener(View->{
            Intent tocommunity=new Intent(this,CommunityDiagnosisActivity.class);
            startActivity(tocommunity);
        });
        c6.setOnClickListener(View->{
            Intent toai=new Intent(this,AIDoctorMatchingActivity.class);
            startActivity(toai);
        });


    }
}