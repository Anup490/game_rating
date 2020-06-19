package com.anup.gamedomain.api;

import java.io.InputStream;

public interface Game {

    String getName();

    String getDescription();

    int getRating();

    InputStream getPhoto();

}
