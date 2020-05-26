package com.setsoft.ydskelimelik.presenter;

import com.setsoft.ydskelimelik.util.IConstant;
import com.setsoft.ydskelimelik.view.IMainView;
import com.setsoft.ydskelimelik.view.MainActivity;

public class Presenter implements IPresenter{

    private IMainView view;//contructor injection

    public Presenter(IMainView view) {

        this.view = view;
    }

    @Override
    public void onCreated() {
        view.initView();
    }

    @Override
    public void onButtonAddWordClicked() {
        view.showDialog();

    }

    @Override
    public void onButtonClearClicked() {


    }
}
