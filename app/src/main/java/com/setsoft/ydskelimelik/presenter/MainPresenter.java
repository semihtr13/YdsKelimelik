package com.setsoft.ydskelimelik.presenter;

import android.content.Context;

import com.setsoft.ydskelimelik.model.Word;
import com.setsoft.ydskelimelik.model.dao.WordDAO;
import com.setsoft.ydskelimelik.view.IMainView;

public class MainPresenter implements IMainPresenter {
    private IMainView view;
    private Context context;
    private WordDAO wordDAO;

    public MainPresenter(IMainView view, Context context) {
        this.view = view;
        this.context = context;
        wordDAO = new WordDAO(context);
    }

    @Override
    public void getProgressData() {
        view.loadMainData(wordDAO.levelStatus(), wordDAO.countAllWord(), wordDAO.countStateWord(), wordDAO.countUnstateWord());
    }

    @Override
    public void onAddClick(String english, String turkish) {
        Word word = new Word();
        word.setEnglish(english.toLowerCase());
        word.setTurkish(turkish.toLowerCase());
        wordDAO.insertWord(word);
        getProgressData();
        view.onSuccess();
    }

}

