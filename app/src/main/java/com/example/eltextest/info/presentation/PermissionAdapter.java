package com.example.eltextest.info.presentation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eltextest.R;

import java.util.List;

public class PermissionAdapter extends RecyclerView.Adapter<PermissionAdapter.PermissionViewHolder> {

    private final List<String> permissionList;

    public PermissionAdapter(List<String> permissionList) {
        this.permissionList = permissionList;
    }

    @NonNull
    @Override
    public PermissionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.permission_list_element,
                parent,
                false
        );
        return new PermissionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PermissionViewHolder holder, int position) {
        holder.bind(permissionList.get(position));
    }

    @Override
    public int getItemCount() {
        return permissionList.size();
    }

    static class PermissionViewHolder extends RecyclerView.ViewHolder {

        View view;
        TextView permissionName;

        public PermissionViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            permissionName = view.findViewById(R.id.element_text);
        }

        public void bind(String permission) {
            permissionName.setText(permission);
        }
    }
}
