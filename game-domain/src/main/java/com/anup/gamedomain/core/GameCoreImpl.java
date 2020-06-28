package com.anup.gamedomain.core;

import com.anup.gamedomain.api.GameRequest;
import com.anup.gamedomain.api.GameResponse;
import com.anup.gamedomain.enums.ValidationStatus;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class GameCoreImpl implements GameCore {

    private EntityManager entityManager;
    private GameValidator validator;

    public GameCoreImpl(EntityManager entityManager, GameValidator validator){
        this.entityManager = entityManager;
        this.validator = validator;
    }

    public GameResponse insertGame(GameRequest gameRequest){
        ValidationStatus status = validator.validate(gameRequest);
        if(status != ValidationStatus.OK){
            return toResponse(status,null);
        }
        entityManager.persist(toEntity(gameRequest));
        return toResponse(status,null);
    }

    public GameResponse fetchGameByName(GameRequest gameRequest){
        if(validator.hasName(gameRequest)){
            Object entity = entityManager.createQuery("select g from GameEntity g where g.name=: name")
                    .setParameter("name", gameRequest.getName())
                    .getResultList()
                    .get(0);
            return toResponse(ValidationStatus.OK, entity);
        }
        return toResponse(ValidationStatus.NO_NAME,null);
    }

    public GameResponse updateGameByName(GameRequest gameRequest){
        ValidationStatus status = validator.validate(gameRequest);
        if(status != ValidationStatus.OK){
            return toResponse(status,null);
        }
        Object object = entityManager.createQuery("select g from GameEntity g where g.name=: name")
                .setParameter("name", gameRequest.getName())
                .getResultList()
                .get(0);
        GameEntity entity = (GameEntity)object;
        entity.setDescription(gameRequest.getDescription());
        entity.setRating(gameRequest.getRating());
        entity.setPhoto(gameRequest.getPhoto());

        Query query = entityManager.createQuery("update GameEntity g set g.description=: description, g.photo=: photo, g.rating=: rating where g.name=: name");
        query.setParameter("description", gameRequest.getDescription());
        query.setParameter("photo", gameRequest.getPhoto());
        query.setParameter("rating", gameRequest.getRating());
        query.setParameter("name", entity.getName());
        query.executeUpdate();

        return toResponse(ValidationStatus.OK, null);
    }

    private GameResponse toResponse(ValidationStatus status, Object data){
        return new GameResponseImpl(status.getCode(),status.getMessage(),data);
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
