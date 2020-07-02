package com.anup.gamecore.persistance;

import com.anup.gamecore.dto.GameRequest;

public interface GamePersistor {
    void persistGame(GameRequest gameRequest);
    Object fetchGameByName(String name);
    void updateGameByName(GameRequest gameRequest);
}
