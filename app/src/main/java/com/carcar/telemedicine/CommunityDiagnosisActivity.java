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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class CommunityDiagnosisActivity extends AppCompatActivity {
    private EditText userQueryInput;
    private Button submitQueryButton;
    private ListView communityResponseList;
    private List<String> communityResponses;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_community_diagnosis);

        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.neon_violate));

        userQueryInput = findViewById(R.id.userQueryInput);
        submitQueryButton = findViewById(R.id.submitQueryButton);
        communityResponseList = findViewById(R.id.communityResponseList);

        communityResponses = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, communityResponses);
        communityResponseList.setAdapter(adapter);

        submitQueryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitQuery();
            }
        });
    }

    private void submitQuery() {
        String query = userQueryInput.getText().toString().trim();
        if (!query.isEmpty()) {
            communityResponses.add("You asked: " + query + "\nCommunity will respond soon.");
            adapter.notifyDataSetChanged();
            userQueryInput.setText("");
        }
    }
}