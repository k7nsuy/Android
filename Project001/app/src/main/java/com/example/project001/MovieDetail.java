package com.example.project001;

import java.io.Serializable;

public class MovieDetail implements Serializable {

    String title;
    String director;
    String actor;

    int[] imgIds;

    public MovieDetail() {
    }

    public MovieDetail(String title, String director, String actor, int[] imgIds) {
        this.title = title;
        this.director = director;
        this.actor = actor;
        this.imgIds = imgIds;
    }

    public int[] getImgIds() {
        return imgIds;
    }

    public void setImgIds(int[] imgIds) {
        this.imgIds = imgIds;
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
}
