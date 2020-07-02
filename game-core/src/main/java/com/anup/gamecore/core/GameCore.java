package com.anup.gamecore.core;

import com.anup.gamecore.dto.GameRequest;
import com.anup.gamecore.dto.GameResponse;

public interface GameCore {
    GameResponse insertGame(GameRequest game);
    GameResponse fetchGameByName(GameRequest game);
    GameResponse updateGameByName(GameRequest game);
}
