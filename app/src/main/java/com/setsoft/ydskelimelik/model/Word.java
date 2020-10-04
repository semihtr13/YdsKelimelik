package com.setsoft.ydskelimelik.model;

public class Word {

    private int id;
    private String turkish;
    private String english;
    private int state;
    private int learnDate;
    private int insertDate;

    public Word(int id, String turkish, String english, int state, int learnDate, int insertDate) {
        this.id = id;
        this.turkish = turkish;
        this.english = english;
        this.state = state;
        this.learnDate = learnDate;
        this.insertDate = insertDate;
    }

    public Word() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTurkish() {
        return turkish;
    }

    public void setTurkish(String turkish) {
        this.turkish = turkish;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getLearnDate() {
        return learnDate;
    }

    public void setLearnDate(int learnDate) {
        this.learnDate = learnDate;
    }

    public int getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(int insertDate) {
        this.insertDate = insertDate;
    }
}
