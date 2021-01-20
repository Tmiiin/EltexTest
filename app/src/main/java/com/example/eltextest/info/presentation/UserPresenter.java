package com.example.eltextest.info.presentation;

import android.view.View;

import com.example.eltextest.info.ui.UserFragment;

public class UserPresenter {

    private UserFragment view;

    public void attachView(UserFragment view){
        this.view = view;
    }
}
