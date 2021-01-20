package com.example.eltextest.info.ui;

import android.os.Bundle;
import android.util.Log;
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
import com.example.eltextest.info.presentation.UserPresenter;
import com.example.eltextest.login.data.model.UserInfoResponse;

import org.w3c.dom.Text;

public class UserFragment extends Fragment {

    UserPresenter presenter;
    UserInfoResponse userInfoResponse;
    PermissionAdapter permissionAdapter;
    private final String TAG = "UserInfoFragment";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        userInfoResponse = getArguments().getParcelable("info");
        return inflater.inflate(R.layout.userinfo_layout, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initPresenter();
        initAdapter();
        setRetainInstance(true);
        initView(view);
    }

    private void initView(View view){
        TextView userEmail = view.findViewById(R.id.user_email);
        userEmail.setText(userInfoResponse.getEmail());
        TextView roleId = view.findViewById(R.id.role_id);
        roleId.setText(userInfoResponse.getRoleId());
        TextView username = view.findViewById(R.id.username);
        username.setText(userInfoResponse.getUsername());
    }

    private void initPresenter() {
        presenter = new UserPresenter();
        presenter.attachView(this);
    }

    private void initAdapter(){
        permissionAdapter = new PermissionAdapter(userInfoResponse.getPermissions());
        RecyclerView recyclerView = this.getView().findViewById(R.id.permission_list);
        recyclerView.setAdapter(permissionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
