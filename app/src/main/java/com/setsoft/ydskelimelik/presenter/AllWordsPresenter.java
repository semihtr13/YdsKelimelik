package com.setsoft.ydskelimelik.presenter;

import android.content.Context;

import com.setsoft.ydskelimelik.model.dao.WordDAO;
import com.setsoft.ydskelimelik.view.IAllWordsView;

public class AllWordsPresenter implements IAllWordsPresenter {
    private Context context;
    private IAllWordsView view;
    private WordDAO wordDAO;

    public AllWordsPresenter(Context context, IAllWordsView view) {
        this.context = context;
        this.view = view;
        wordDAO=new WordDAO(context);
    }

    @Override
    public void onTodayClick() {
        view.setAdapterData(wordDAO.getTodayDateList());

    }

    @Override
    public void onWeeklyClick() {
        view.setAdapterData(wordDAO.getWeakDateList());

    }

    @Override
    public void onMonthlyClick() {
        view.setAdapterData(wordDAO.getMonthDateList());

    }

    @Override
    public void onAllWordsClick() {
        view.setAdapterData(wordDAO.allWordList());

    }
}
