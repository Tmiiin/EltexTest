package com.example.eltextest.login.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.eltextest.R;
import com.example.eltextest.info.ui.UserFragment;
import com.example.eltextest.login.data.model.UserInfoResponse;
import com.example.eltextest.login.di.LoginPresenterFactory;
import com.example.eltextest.login.presentation.LoginPresenter;

import java.util.ArrayList;
import java.util.List;

public class LoginFragment extends Fragment {

    private final String TAG = "main";
    private final String TOKEN = "token";
    EditText editLogin;
    EditText editPassword;
    Button enterButton;
    LoginPresenter presenter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_layout, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initPresenter();
        presenter.checkNeedLogin();
    }

    private void initPresenter() {
        presenter = LoginPresenterFactory.getInstance().create();
        presenter.attachView(this);
    }

    private void initLoginView(View view) {
        view.findViewById(R.id.enter_view_container).setVisibility(View.VISIBLE);
        view.findViewById(R.id.enter_progress_bar).setVisibility(View.GONE);
        hideChildProgressBar();
        editLogin = view.findViewById(R.id.edit_login);
        editPassword = view.findViewById(R.id.edit_password);
        enterButton = view.findViewById(R.id.enter_button);
        enterButton.setOnClickListener(button -> {
            String login = editLogin.getText().toString();
            String password = editPassword.getText().toString();
            showChildProgressBar();
            presenter.tryLogin(login, password);
        });
    }

    public void showEnterScreen(){
        initLoginView(this.getView());
    }

    public void showChildProgressBar(){
        this.getView().findViewById(R.id.enter_child_progress_bar).setVisibility(View.VISIBLE);
    }

    public void hideChildProgressBar(){
        this.getView().findViewById(R.id.enter_child_progress_bar).setVisibility(View.GONE);
    }
    public void makeErrorToast(String errorReason) {
        Toast.makeText(getContext(), errorReason, Toast.LENGTH_LONG).show();
    }

    public void goToInfoView(UserInfoResponse userInfoResponse) {
        UserFragment userFragment = new UserFragment();
        Bundle args = new Bundle();
        args.putParcelable("info", userInfoResponse);
        userFragment.setArguments(args);
        Log.i(TAG, "Go to user info view");
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, userFragment, TAG)
                //.addToBackStack(null)
                .commit();
    }

    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
