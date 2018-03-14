package com.android.rvkmr.task4.Models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by rvkmr on 10-03-2018.
 */

public class LibraryPOJO extends RealmObject{
    public LibraryPOJO() {
    }

    private String letter;
    private String data;


    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
