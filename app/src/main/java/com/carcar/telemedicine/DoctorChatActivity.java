package com.carcar.telemedicine;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DoctorChatActivity extends AppCompatActivity {

    private RecyclerView chatRecyclerView;
    private EditText messageInput;
    private ImageButton videoCallButton, audioCallButton;
    private FloatingActionButton sendButton;
    private ChatAdapter2 chatAdapter2;
    private List<String> chatMessages;
    private ImageView profile2;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_chat);

        Window window=getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.neon_violate));

        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String profileType = sharedPreferences.getString("profileType", ""); // Default: empty string

        // Initialize UI components
        chatRecyclerView = findViewById(R.id.chatRecyclerView);
        messageInput = findViewById(R.id.messageInput);
        sendButton = findViewById(R.id.sendbutton);
        videoCallButton = findViewById(R.id.videoCallButton);
        audioCallButton = findViewById(R.id.audioCallButton);
        profile2=findViewById(R.id.profile);
        tv=findViewById(R.id.tv);


        if ("Doctor Profile".equals(profileType)) {
            profile2.setImageResource(R.drawable.doctor);
        } else if ("Patient Profile".equals(profileType)) {
            tv.setText("Patient1");
            profile2.setImageResource(R.drawable.patient);
        }


        // Initialize chat messages list and adapter
        chatMessages = new ArrayList<>();
        chatAdapter2=new ChatAdapter2(chatMessages);

        // Setup RecyclerView
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatRecyclerView.setAdapter(chatAdapter2);

        sendButton.setOnClickListener(v -> {
            String message = messageInput.getText().toString().trim();
            if (!message.isEmpty()) {
                chatMessages.add(message); // Add message to the list
                chatAdapter2.notifyItemInserted(chatMessages.size() - 1); // Notify adapter
                chatRecyclerView.smoothScrollToPosition(chatMessages.size() - 1); // Scroll to latest message
                messageInput.setText(""); // Clear input field
            }
        });

        videoCallButton.setOnClickListener(v ->
                Toast.makeText(this, "Starting Video Call...", Toast.LENGTH_SHORT).show()
        );

        audioCallButton.setOnClickListener(v ->
                Toast.makeText(this, "Starting Audio Call...", Toast.LENGTH_SHORT).show()
        );
    }
}
