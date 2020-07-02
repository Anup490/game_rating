package com.anup.gamedomain.persistance;

import com.anup.gamecore.dto.GameRequest;
import com.anup.gamecore.persistance.GamePersistor;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class JPAPersistor implements GamePersistor {

    private EntityManager entityManager;

    public JPAPersistor(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void persistGame(GameRequest gameRequest) {
        entityManager.persist(toEntity(gameRequest));
    }

    public Object fetchGameByName(String name) {
        return entityManager.createQuery("select g from GameEntity g where g.name=: name")
                .setParameter("name", name)
                .getResultList()
                .get(0);
    }

    public void updateGameByName(GameRequest gameRequest) {
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
