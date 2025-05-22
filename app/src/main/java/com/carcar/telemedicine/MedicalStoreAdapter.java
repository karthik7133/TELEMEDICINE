package com.carcar.telemedicine;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MedicalStoreAdapter extends RecyclerView.Adapter<MedicalStoreAdapter.ViewHolder> {

    private List<MedicalStore> storeList;
    private List<MedicalStore> storeListFull;

    public MedicalStoreAdapter(List<MedicalStore> storeList) {
        this.storeList = storeList;
        this.storeListFull = new ArrayList<>(storeList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medical_store, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MedicalStore store = storeList.get(position);
        holder.storeName.setText(store.getName());
        holder.storeAddress.setText(store.getAddress());

        holder.callButton.setOnClickListener(v -> {
            Context context = v.getContext();
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + store.getPhone()));
            context.startActivity(callIntent);
        });
    }

    @Override
    public int getItemCount() { return storeList.size(); }

    public void filter(String query) {
        storeList.clear();
        if (query.isEmpty()) {
            storeList.addAll(storeListFull);
        } else {
            for (MedicalStore store : storeListFull) {
                if (store.getName().toLowerCase().contains(query.toLowerCase()) ||
                        store.getAddress().toLowerCase().contains(query.toLowerCase())) {
                    storeList.add(store);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView storeName, storeAddress;
        Button callButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            storeName = itemView.findViewById(R.id.storeName);
            storeAddress = itemView.findViewById(R.id.storeAddress);
            callButton = itemView.findViewById(R.id.callButton);
        }
    }
}
