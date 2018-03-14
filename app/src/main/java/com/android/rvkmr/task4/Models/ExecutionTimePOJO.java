package com.android.rvkmr.task4.Models;

/**
 * Created by rvkmr on 10-03-2018.
 */

public class ExecutionTimePOJO {
    String letter;
    long realm;
    long sqlite;

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public long getRealm() {
        return realm;
    }

    public void setRealm(long realm) {
        this.realm = realm;
    }

    public long getSqlite() {
        return sqlite;
    }

    public void setSqlite(long sqlite) {
        this.sqlite = sqlite;
    }
}
