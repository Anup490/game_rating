package com.anup.gamedomain.core;

import com.anup.gamedomain.api.GameRequest;
import com.anup.gamedomain.api.GameResponse;
import com.anup.gamedomain.utils.StringUtils;

import javax.persistence.EntityManager;

public class GameCore {

    private EntityManager entityManager;

    public GameCore(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public GameResponse processGame(GameRequest gameRequest){
        if(StringUtils.isBlank(gameRequest.getName())){
            return new GameResponseImpl(202,"Name is blank",null);
        }else if(StringUtils.isBlank(gameRequest.getDescription())){
            return new GameResponseImpl(202,"Description is blank",null);
        }else if(gameRequest.getRating() == -1){
            return new GameResponseImpl(202,"Rating is blank",null);
        }else if(hasNoContent(gameRequest.getPhoto())){
            return new GameResponseImpl(202,"No file present",null);
        }else{
            entityManager.persist(toEntity(gameRequest));
            return new GameResponseImpl(200,"Success",null);
        }
    }

    private boolean hasNoContent(byte[] data){
        return (data == null) || (data.length == 0);
    }

    private GameEntity toEntity(GameRequest gameRequest){
        GameEntity gameEntity = new GameEntity();
        gameEntity.setName(gameRequest.getName());
        gameEntity.setPhoto(gameRequest.getPhoto());
        gameEntity.setDescription(gameRequest.getDescription());
        gameEntity.setRating(gameRequest.getRating());
        return gameEntity;
    }

}
