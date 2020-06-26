package com.anup.gamedomain.beans;

import com.anup.gamedomain.api.GameBean;
import com.anup.gamedomain.api.GameRequest;
import com.anup.gamedomain.api.GameResponse;
import com.anup.gamedomain.core.GameCore;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class GameBeanImpl implements GameBean {

    @PersistenceContext(unitName = "GamePersistenceUnit")
    private EntityManager entityManager;
    private GameCore core;

    @PostConstruct
    public void postConstruct(){
        core = new GameCore(entityManager);
    }

    public GameResponse processGameData(GameRequest gameRequest){
        return core.processGame(gameRequest);
    }

}
