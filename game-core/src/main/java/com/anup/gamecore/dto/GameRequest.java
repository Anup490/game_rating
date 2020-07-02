package com.anup.gamecore.dto;

import com.anup.gamecore.utils.StringUtils;

public class GameRequest {

    private String name;
    private String description;
    private int rating;
    private byte[] photo;

    private GameRequest(){}

    private GameRequest(Builder builder){
        this.name = builder.name;
        this.description = builder.description;
        this.rating = builder.rating;
        this.photo = builder.photo;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getRating() {
        return rating;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public static class Builder{
        String name;
        String description;
        int rating;
        byte[] photo;

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

        public Builder setPhoto(byte[] photo) {
            this.photo = photo;
            return this;
        }

        public GameRequest build(){
            return new GameRequest(this);
        }
    }
}
