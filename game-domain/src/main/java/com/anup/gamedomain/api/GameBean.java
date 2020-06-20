package com.anup.gamedomain.api;

import javax.ejb.Local;

@Local
public interface GameBean {
    GameResponse processGameData(GameRequest game);
}
