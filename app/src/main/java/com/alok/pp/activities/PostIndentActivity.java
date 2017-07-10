package com.alok.pp.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.EditText;

import com.alok.pp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;

/**
 * Created by cuelogic on 09/07/17.
 */

public class PostIndentActivity extends BaseActivity {

    @BindView(R.id.checkBoxTankFull)
    CheckBox checkBoxTankFull;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_indent);
        ButterKnife.bind(this);
    }

    @OnTextChanged(R.id.editTextQuantity)
    public void onQuatntityTextChanged(CharSequence s, int start, int before, int count){
        if(count > 0)
            checkBoxTankFull.setEnabled(false);
    }


}
