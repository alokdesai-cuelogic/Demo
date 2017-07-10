package com.alok.pp.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alok.pp.R;

import butterknife.ButterKnife;

/**
 * Created by cuelogic on 09/07/17.
 */

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }
}
