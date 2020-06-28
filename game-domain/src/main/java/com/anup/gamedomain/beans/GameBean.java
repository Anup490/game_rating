package com.anup.gamedomain.beans;

import com.anup.gamedomain.api.GameRequest;
import com.anup.gamedomain.api.GameResponse;
import com.anup.gamedomain.core.GameCore;
import com.anup.gamedomain.core.GameCoreImpl;
import com.anup.gamedomain.core.GameValidator;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class GameBean {

    @PersistenceContext(unitName = "GamePersistenceUnit")
    private EntityManager entityManager;
    private GameCore core;

    @PostConstruct
    public void postConstruct(){
        core = new GameCoreImpl(entityManager, new GameValidator());
    }

    public GameResponse insertGameData(GameRequest gameRequest){
        return core.insertGame(gameRequest);
    }

    public GameResponse fetchGameDataByName(GameRequest gameRequest) {
        return core.fetchGameByName(gameRequest);
    }

    public GameResponse updateGameDataByName(GameRequest gameRequest) {
        return core.updateGameByName(gameRequest);
    }

}
