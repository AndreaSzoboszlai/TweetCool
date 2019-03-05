package com.codecool.web.model;

import java.util.Date;

public class Tweet {
    private int id;
    private String posterName;
    private String content;
    private Date timestamp;

    public Tweet(int id, String posterName, String content, Date timestamp) {
        this.id = id;
        this.posterName = posterName;
        this.content = content;
        this.timestamp = timestamp;
    }


}
