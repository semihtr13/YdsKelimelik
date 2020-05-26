package com.setsoft.ydskelimelik.presenter;

import com.setsoft.ydskelimelik.view.IMainView;

public class Presenter implements IPresenter {
    private IMainView view;
    public Presenter(IMainView view)
    {
        this.view=view;
    }

    @Override
    public void onCreated() {
        view.initView();
    }

    @Override
    public void onButtonAddWordClicked() {
        view.showDialog();

    }
}
