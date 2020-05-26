package com.setsoft.ydskelimelik.model.worddao;

import java.util.ArrayList;
import java.util.List;

public interface IDBService<T> {

    boolean insertWord( T type);

    List<T> allWordList();

    boolean updateWord(T type, int id);

    boolean deleteWord(int id);

    boolean stateUpdateWord( int id, int state);

    List<T> findByDate(int date1);

    List<T> findByState( int state);

    List<T> findByDateAndState(int date1, int state);

    int countAllWord();

    int countStateWord();

    int countUnstateWord();

    int levelStatus();

}
