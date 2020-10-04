package com.setsoft.ydskelimelik.model.dao;

import java.util.List;

public interface IBaseDao<T> {

    boolean insertWord(T type);

    List<T> allWordList();

    boolean updateWord(T type, int id);

    boolean deleteAllWords();

    boolean deleteWord(int id);

    boolean stateUpdateWord(int id, int state);

    List<T> getTodayDateList();

    List<T> getWeakDateList();

    List<T> getMonthDateList();

    List<T> findByState(int state);

    List<T> findByDateAndState(int date1, int state);

    int countAllWord();

    int countStateWord();

    int countUnstateWord();

    int levelStatus();

}
