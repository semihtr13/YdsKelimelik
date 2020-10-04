package com.setsoft.ydskelimelik.view;

public interface IMainView {
    void loadMainData(int level,int allWordsCount,int countStateWord,int countUnstateWord);
    void onError(String message);
    void onSuccess();
    void showDialog();
    void clearDialog();
}
