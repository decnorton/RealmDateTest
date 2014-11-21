package com.decnorton.realmdatetest;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by decnorton on 21/11/14.
 */
public class Test extends RealmObject {

    private long id;
    private Date date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
