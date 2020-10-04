package com.setsoft.ydskelimelik.util;

public interface IConstant {


    interface IDBConstant {
        String DB_NAME = "YDSKELIMELIK";
        String TABLE_NAME = "WORDS";
        String ID = "ID";
        String TURKISH = "TURKISH";
        String ENGLISH = "ENGLISH";
        String STATE = "STATE";
        String LEARNDATE = "LEARNDATE";
        String INSERTDATE = "INSERTDATE";
    }
    interface ErrorMessage{
        String EMPY_FIELD_ERROR="Boş alanları doldurunuz.";
    }
    interface SuccessMessage{
        String WORD_ADDED="Kelime eklendi.";
    }
}
