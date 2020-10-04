package com.setsoft.ydskelimelik.util;

public class Validation {
    public static boolean validate(String en, String tr) {
       return !Utils.isNullOrEmpty(en)&&!Utils.isNullOrEmpty(tr);
    }
}
