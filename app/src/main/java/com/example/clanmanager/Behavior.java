package com.example.clanmanager;

import java.util.Date;

public class Behavior {
    String id;
    String thumb;
    Date date;
    String comment;

    public Behavior() {
    }

    public Behavior(String id, String thumb, Date date, String comment) {
        this.id = id;
        this.thumb = thumb;
        this.date = date;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
