package com.carcar.telemedicine;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Window;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import pl.droidsonroids.gif.GifDrawable;

public class SplasScreenActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "MyAppPrefs";
    private static final String PREF_DONT_SHOW_AGAIN = "dontShowAgain";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splas_screen);

        Window window=getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.neon_violate));

        //SET DEFAULT USERS...
        insertDefaultUsersIfNeeded();

        ImageView imageView = findViewById(R.id.cardimage);

        // Load GIF safely
        try {
            GifDrawable gifDrawable = new GifDrawable(getResources(), R.drawable.telemedicine);
            gifDrawable.setSpeed(0.5f);
            imageView.setImageDrawable(gifDrawable);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            boolean dontShowAgain = preferences.getBoolean(PREF_DONT_SHOW_AGAIN, false);

            Intent nextIntent = dontShowAgain ?
                    new Intent(this, profileactivity.class) :
                    new Intent(this, profileactivity.class);

            startActivity(nextIntent);
            finish();
        }, 1500);
    }
    private void insertDefaultUsersIfNeeded() {
        SharedPreferences prefs = getSharedPreferences("MYAPKTEST", Context.MODE_PRIVATE);
        boolean isFirstRun = prefs.getBoolean("isFirstRun", true);

        if (isFirstRun) {
            UserDatabase db = UserDatabase.getInstance(this);
            LoginDao loginDao = db.loginDao();

            // Example default users
            LoginStruct user1 = new LoginStruct("Doctor1", "KARTHIK");
            LoginStruct user2 = new LoginStruct("Doctor2", "MOUNI");

            loginDao.insert(user1);
            loginDao.insert(user2);

            // Mark that the app has inserted the data
            prefs.edit().putBoolean("isFirstRun", false).apply();
        }
    }
}
