package com.example.eltextest.login.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eltextest.R;

import java.util.Arrays;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.add(R.id.main_container, new LoginFragment()).commit();
    }


}