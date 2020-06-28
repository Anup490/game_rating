package com.anup.gamedomain.core;

import com.anup.gamedomain.api.GameRequest;
import com.anup.gamedomain.api.GameResponse;

public interface GameCore {
    GameResponse insertGame(GameRequest game);
    GameResponse fetchGameByName(GameRequest game);
    GameResponse updateGameByName(GameRequest game);
}
