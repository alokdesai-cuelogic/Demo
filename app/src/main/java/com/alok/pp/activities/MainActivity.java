package com.alok.pp.activities;

import android.content.Intent;
import android.os.Bundle;

import com.alok.pp.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonPostIndent)
    public void postIndent(){
        Intent intent = new Intent(this,PostIndentActivity.class);
        startActivity(intent);
    }

}
