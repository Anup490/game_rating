package com.anup.gamedomain.api;

import java.io.InputStream;

public interface GameRequest {

    String getName();

    String getDescription();

    int getRating();

    InputStream getPhoto();

    byte[] getBuffer();

}
