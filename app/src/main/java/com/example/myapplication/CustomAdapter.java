package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomVIewHolder> {

    private Context mCtx;
    private List<Client> client;

    public CustomAdapter(Context nCtx, List<Client> client) {
        this.mCtx = nCtx;
        this.client = client;
    }

    @NonNull
    @Override
    public CustomVIewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.custom_lis, parent, false);
        return new CustomVIewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomVIewHolder holder, int position) {

        Client list = client.get(position);
        holder.name.setText(list.getName());
        holder.number.setText(list.getPhone());

    }

    @Override
    public int getItemCount() {
        return client.size();
    }

    public class CustomVIewHolder extends RecyclerView.ViewHolder {

        TextView sort_number, number,name;

        public CustomVIewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            number = itemView.findViewById(R.id.number);
            sort_number = itemView.findViewById(R.id.sort_name);
        }
    }
}
