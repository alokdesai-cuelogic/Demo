package com.alok.pp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.alok.pp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by cuelogic on 09/07/17.
 */

public class RequestCodeActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_code);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonRequestCode)
    public void requestCode(){
        Intent intent = new Intent(this,RegistrationActivity.class);
        startActivity(intent);
    }
}
