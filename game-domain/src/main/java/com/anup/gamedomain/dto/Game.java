package com.anup.gamedomain.dto;

import com.anup.gamedomain.utils.StringUtils;

import java.io.InputStream;

public class Game {

    private String name;
    private String description;
    private int rating;
    private InputStream photo;

    private Game(){}

    private Game(Builder builder){
        this.name = builder.name;
        this.description = builder.description;
        this.rating = builder.rating;
        this.photo = builder.photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public InputStream getPhoto() {
        return photo;
    }

    public void setPhoto(InputStream photo) {
        this.photo = photo;
    }

    public static class Builder{
        String name;
        String description;
        int rating;
        InputStream photo;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setRating(String rating) {
            this.rating = StringUtils.toInteger(rating);
            return this;
        }

        public Builder setPhoto(InputStream photo) {
            this.photo = photo;
            return this;
        }

        public Game build(){
            return new Game(this);
        }

    }


}
