package com.zsh.learn.music;

/**
 * Created by zsh on 2017/7/11.
 */

public class MusicInfo {
    private String name;
    private String author;
    private String url;

    public MusicInfo(String name, String url, String author) {
        this.name = name;
        this.url = url;
        this.author = author;
    }

    @Override
    public String toString() {
        return "MusicInfo{" +
                "author='" + author + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
