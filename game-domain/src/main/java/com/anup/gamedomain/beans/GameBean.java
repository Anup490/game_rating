package com.anup.gamedomain.beans;

import com.anup.gamecore.core.GameCore;
import com.anup.gamecore.dto.GameRequest;
import com.anup.gamecore.dto.GameResponse;
import com.anup.gamecore.core.GameCoreImpl;
import com.anup.gamecore.validation.GameValidatorImpl;
import com.anup.gamedomain.persistance.JPAPersistor;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
@RolesAllowed("developer")
public class GameBean {

    @PersistenceContext(unitName = "GamePersistenceUnit")
    private EntityManager entityManager;
    private GameCore core;

    @PostConstruct
    public void postConstruct(){
        core = new GameCoreImpl(new JPAPersistor(entityManager), new GameValidatorImpl());
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
