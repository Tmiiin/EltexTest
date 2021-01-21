package com.example.eltextest.info.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eltextest.R;
import com.example.eltextest.info.presentation.PermissionAdapter;
import com.example.eltextest.login.data.model.UserInfoResponse;

public class UserFragment extends Fragment {

    UserInfoResponse userInfoResponse;
    PermissionAdapter permissionAdapter;
    private final String TAG = "UserInfoFragment";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        userInfoResponse = getArguments().getParcelable(getString(R.string.info));
        return inflater.inflate(R.layout.userinfo_layout, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
        initView(view);
    }

    private void initView(View view){
        TextView userEmail = view.findViewById(R.id.user_email);
        TextView username = view.findViewById(R.id.username);
        TextView roleId = view.findViewById(R.id.role_id);
        userEmail.setText(userInfoResponse.getEmail());
        username.setText(userInfoResponse.getUsername());
        roleId.setText(userInfoResponse.getRoleId());
    }

    private void initAdapter(){
        permissionAdapter = new PermissionAdapter(userInfoResponse.getPermissions());
        RecyclerView recyclerView = this.getView().findViewById(R.id.permission_list);
        recyclerView.setAdapter(permissionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
