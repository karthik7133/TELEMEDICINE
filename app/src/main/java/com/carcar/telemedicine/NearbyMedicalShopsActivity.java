package com.carcar.telemedicine;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class NearbyMedicalShopsActivity extends AppCompatActivity {

    private RecyclerView medicalStoresRecyclerView;
    private EditText searchBar;
    private FloatingActionButton fabNearestStore;
    private MedicalStoreAdapter adapter;
    private List<MedicalStore> storeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_medical_shops);

        searchBar = findViewById(R.id.searchBar);
        fabNearestStore = findViewById(R.id.fab_nearest_store);
        medicalStoresRecyclerView = findViewById(R.id.medicalStoresRecyclerView);

        medicalStoresRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MedicalStoreAdapter(storeList);
        medicalStoresRecyclerView.setAdapter(adapter);

        loadMedicalStores();

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        fabNearestStore.setOnClickListener(v -> {
            Uri gmmIntentUri = Uri.parse("geo:0,0?q=Nearby+Medical+Stores");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        });
    }

    private void loadMedicalStores() {
        storeList.add(new MedicalStore("Apollo Pharmacy", "123, Main Street", "9876543210"));
        storeList.add(new MedicalStore("MedPlus", "456, Park Avenue", "9876543222"));
        storeList.add(new MedicalStore("Guardian Pharmacy", "789, City Center", "9876543233"));
        adapter.notifyDataSetChanged();
    }
}
