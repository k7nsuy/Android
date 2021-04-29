package com.example.a21_04_29_webconnect;

import java.io.Serializable;

public class Board implements Serializable {

    private int bno;
    private String title;
    private String content;
    private String writer;
    private String post_date;

    private Board() {
        super();
    }

    public Board(int bno, String title, String content, String writer, String post_date) {
        this.bno = bno;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.post_date = post_date;
    }

    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }

    @Override
    public String toString() {
        return "Board{" +
                "bno=" + bno +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", post_date='" + post_date + '\'' +
                '}';
    }
}
