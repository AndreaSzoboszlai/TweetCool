package com.codecool.web.model;

import java.util.Date;
import java.util.Objects;

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

    public int getId() {
        return id;
    }

    public String getPosterName() {
        return posterName;
    }

    public String getContent() {
        return content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Tweet)) return false;
        Tweet tweet = (Tweet) o;
        return this.id == tweet.id &&
            this.posterName.equals(tweet.posterName) &&
            this.content.equals(tweet.content);
    }

    @Override
    public String toString() {
        return "Tweet{" +
            "id=" + id +
            ", posterName='" + posterName + '\'' +
            ", content='" + content + '\'' +
            ", timestamp=" + timestamp +
            '}';
    }
}
