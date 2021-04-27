package com.example.a21_04_27_movie;

import java.io.Serializable;

// Serializable => 다른 activity 로 데이터를 보낼 때 사용.
public class Movie implements Serializable {

    private int id;
    private int resId;
    private String title;
    private String director;
    private String actor;
    private double point;

    public Movie() {
        super();
    }

    public Movie(int id, int resId, String title, String director, String actor, double point) {
        this.id = id;
        this.resId = resId;
        this.title = title;
        this.director = director;
        this.actor = actor;
        this.point = point;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }
}
