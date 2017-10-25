package com.alok.pp.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.alok.pp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * Created by cuelogic on 09/07/17.
 */

public class PostIndentActivity extends BaseActivity {

    @BindView(R.id.checkBoxTankFull)
    CheckBox checkBoxTankFull;

    @BindView(R.id.editTextAmount)
    EditText editTextAmount;

    @BindView(R.id.editTextDriverCash)
    EditText editTextDriverCash;

    @BindView(R.id.editTextQuantity)
    EditText editTextQuantity;

    @BindView(R.id.spinnerPumpName)
    Spinner spinnerPumpName;

    @BindView(R.id.spinnerVehicleNo)
    Spinner spinnerVehicleNo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_indent);
        ButterKnife.bind(this);
    }

    @OnTextChanged(R.id.editTextQuantity)
    public void onQuatntityTextChanged(CharSequence s, int start, int before, int count){
        if(count > 0) {
            checkBoxTankFull.setEnabled(false);
            editTextAmount.setEnabled(false);
        }else {
            checkBoxTankFull.setEnabled(true);
            editTextAmount.setEnabled(true);


        }
    }

    @OnClick(R.id.buttonSubmit)
    public void onSubmit(){
        AlertDialog.Builder builder = new AlertDialog.Builder(PostIndentActivity.this);
        builder.setTitle("Success");
        builder.setMessage("Indent submitted successfully");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                clearFields();
            }
        });
        builder.show();
    }

    private void clearFields(){
        spinnerPumpName.setSelection(0);
        spinnerVehicleNo.setSelection(0);
        editTextAmount.setText("");
        editTextDriverCash.setText("");
        editTextQuantity.setText("");
    }

}
