package com.anup.gamedomain.api;

public interface GameRequest {
    String getName();
    String getDescription();
    int getRating();
    byte[] getPhoto();
}
