package com.example.rigoroustesting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AlertAdapter extends RecyclerView.Adapter<AlertAdapter.ViewHolder> {
    private final Context context;
    private final List<String> alerts;

    public AlertAdapter(Context context, List<String> alerts) {
        this.context = context;
        this.alerts = alerts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_alert, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String alert = alerts.get(position);
        holder.alertTextView.setText(alert);
    }

    @Override
    public int getItemCount() {
        return alerts.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView alertTextView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            alertTextView = itemView.findViewById(R.id.alertTextView);
        }
    }
}
