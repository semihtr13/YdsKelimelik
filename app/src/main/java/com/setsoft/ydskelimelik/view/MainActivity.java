package com.setsoft.ydskelimelik.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.lzyzsd.circleprogress.ArcProgress;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.setsoft.ydskelimelik.R;
import com.setsoft.ydskelimelik.base.IBaseView;
import com.setsoft.ydskelimelik.presenter.MainPresenter;
import com.setsoft.ydskelimelik.util.IConstant;
import com.setsoft.ydskelimelik.util.Utils;
import com.setsoft.ydskelimelik.util.Validation;

public class MainActivity extends AppCompatActivity implements IMainView, IBaseView {
    private Context context=null;
    private EditText etEnglish, etTurkish;
    private ImageView imgFlagEnglish,imgFlagGerman;
    private MainPresenter presenter;
    private Dialog dialog = null;
    private ProgressBar progressLevel;
    private ArcProgress progress1,progress2,progress3;
    private TextView txtLevel;
    private BottomSheetDialog bottomSheetDialog;
    private View bottomSheetView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        presenter = new MainPresenter(this,getApplicationContext());
        presenter.getProgressData();
    }

    @Override
    public void bindViews() {
        context=getApplicationContext();
        progressLevel = findViewById(R.id.progressLevel);
        progress1=findViewById(R.id.arc_progress1);
        progress2=findViewById(R.id.arc_progress2);
        progress3=findViewById(R.id.arc_progress3);
        txtLevel=findViewById(R.id.txtLevel);
        bottomSheetDialog=new BottomSheetDialog(this,R.style.BottomSheetDialog_Theme);
        bottomSheetView= LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_dialog_add_word,(RelativeLayout)findViewById(R.id.bottomsheetContainer));
        etEnglish=bottomSheetView.findViewById(R.id.etEnglish);
        etTurkish=bottomSheetView.findViewById(R.id.etTurkish);
        imgFlagEnglish=bottomSheetView.findViewById(R.id.imgFlagEnglish);
        imgFlagGerman=bottomSheetView.findViewById(R.id.imgFlagGerman);
    }




    @Override
    public void showDialog() {
        bottomSheetView.findViewById(R.id.imgFlagEnglish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            imgFlagEnglish.setBackgroundResource(R.drawable.bg_btn_flag_checked);
            imgFlagGerman.setBackgroundResource(R.drawable.bg_btn_flag);
            etEnglish.setHint("English");
            }
        });
        bottomSheetView.findViewById(R.id.imgFlagGerman).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgFlagGerman.setBackgroundResource(R.drawable.bg_btn_flag_checked);
                imgFlagEnglish.setBackgroundResource(R.drawable.bg_btn_flag);
                etEnglish.setHint("German");
            }
        });
        bottomSheetView.findViewById(R.id.btnClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearDialog();
            }
        });
        bottomSheetView.findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String englistText=etEnglish.getText().toString();
                String turkishText=etTurkish.getText().toString();
                if (Validation.validate(englistText,turkishText)){
                    presenter.onAddClick(englistText,turkishText);
                }
                else {
                    Utils.showMessage(context, IConstant.ErrorMessage.EMPY_FIELD_ERROR,false);
                }
            }
        });
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();
    }

    @Override
    public void loadMainData(int level, int allWord, int countstateWord, int countUnstateWord) {
        progress1.setMax(allWord);
        progress2.setMax(allWord);
        progress3.setMax(allWord);
        progressLevel.setProgress(level);
        txtLevel.setText("% "+level);
        progress1.setProgress(allWord);
        progress2.setProgress(countstateWord);
        progress3.setProgress(countUnstateWord);
    }

    @Override
    public void clearDialog() {
        etEnglish.setText("");
        etTurkish.setText("");
    }

    @Override
    public void onError(String message) {
        Utils.showMessage(context,message,true);
    }

    @Override
    public void onSuccess() {
        Utils.showMessage(context,IConstant.SuccessMessage.WORD_ADDED,true);
        clearDialog();
    }
    public void onAddWordClick(View view) {
        showDialog();
    }

    public void onAllWordsClick(View view) {
        Utils.nextActivity(context,AllWordsActivity.class);
    }

    public void onQuizClick(View view) {
        Utils.nextActivity(context,QuizActivity.class);
    }


}
