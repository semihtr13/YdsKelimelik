package com.setsoft.ydskelimelik.view;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.setsoft.ydskelimelik.R;
import com.setsoft.ydskelimelik.presenter.Presenter;

public class MainActivity extends AppCompatActivity implements IMainView {
    private Spinner spCategories;
    private EditText etEnglish, etTurkish;
    private Button btnClear, btnAdd;
    private Presenter presenter;
    private Dialog dialog = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new Presenter(this);
        presenter.onCreated();


    }

    public void onButtonAddWordClicked(View view) {
        presenter.onButtonAddWordClicked();

    }
    public void onButtonClearClicked(View view) {



    }
    public void onButtonAddClicked(View view) {


    }

    @Override
    public void initView() {


    }

    @Override
    public void showDialog() {
        if (dialog == null) {
            dialog = new Dialog(this);
            dialog.setContentView(R.layout.custom_dialog_add_word);
            etEnglish = dialog.findViewById(R.id.etEnglish);
            etTurkish = dialog.findViewById(R.id.etTurkish);
            spCategories = dialog.findViewById(R.id.spCategories);
            btnAdd = dialog.findViewById(R.id.btnAdd);
            btnClear = dialog.findViewById(R.id.btnClear);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setCancelable(true);
        }
        dialog.show();
    }

}
