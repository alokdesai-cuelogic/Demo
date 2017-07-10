package com.alok.pp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alok.pp.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by cuelogic on 09/07/17.
 */

public class RegistrationActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonRegister)
    public void registerUser(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
