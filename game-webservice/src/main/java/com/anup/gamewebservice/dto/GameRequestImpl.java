package com.anup.gamewebservice.dto;

import com.anup.gamedomain.api.GameRequest;
import com.anup.gamedomain.utils.StringUtils;

import java.io.IOException;
import java.io.InputStream;

public class GameRequestImpl implements GameRequest {

    private String name;
    private String description;
    private int rating;
    private byte[] photo;

    private GameRequestImpl(){}

    private GameRequestImpl(Builder builder){
        this.name = builder.name;
        this.description = builder.description;
        this.rating = builder.rating;
        this.photo = toBytes(new byte[1024], builder.photo);
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

    private byte[] toBytes(byte[] buffer, InputStream inputStream) {
        try{
            StringBuilder stringBuilder = new StringBuilder();
            while (inputStream.read(buffer) > -1){
                stringBuilder.append(new String(buffer));
            }
            if(StringUtils.isBlank(stringBuilder.toString())){
                return null;
            }
            return stringBuilder.toString().getBytes();
        }catch (IOException e){
            return null;
        }
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

        public GameRequestImpl build(){
            return new GameRequestImpl(this);
        }
    }

}
