package com.anup.gamedomain.core;

import com.anup.gamedomain.api.GameRequest;
import com.anup.gamedomain.api.GameResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class GameCoreImplUnitTest {

    @Mock GameRequest mockGameRequest;
    @Mock EntityManager mockEntityManager;
    private GameCoreImpl gameCore;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        Mockito.when(mockGameRequest.getName()).thenReturn("Anup");
        Mockito.when(mockGameRequest.getDescription()).thenReturn("description");
        Mockito.when(mockGameRequest.getRating()).thenReturn(99);
        Mockito.when(mockGameRequest.getPhoto()).thenReturn(new byte[1]);

        gameCore = new GameCoreImpl(mockEntityManager, new GameValidator());
    }

    @Test
    public void test_processGame_whenNameIsBlank(){
        Mockito.when(mockGameRequest.getName()).thenReturn("");

        GameResponse responseActual = gameCore.insertGame(mockGameRequest);

        Assert.assertTrue(isSame(new GameResponseImpl(202,"Name is blank",null), responseActual));
    }

    @Test
    public void test_processGame_whenDescriptionIsBlank(){
        Mockito.when(mockGameRequest.getDescription()).thenReturn("");

        GameResponse responseActual = gameCore.insertGame(mockGameRequest);

        Assert.assertTrue(isSame(new GameResponseImpl(202,"Description is blank",null), responseActual));
    }

    @Test
    public void test_processGame_whenRatingIsMinusOne(){
        Mockito.when(mockGameRequest.getRating()).thenReturn(-1);

        GameResponse responseActual = gameCore.insertGame(mockGameRequest);

        Assert.assertTrue(isSame(new GameResponseImpl(202,"Rating is blank",null), responseActual));
    }

    @Test
    public void test_processGame_whenPhotoIsMissing(){
        Mockito.when(mockGameRequest.getPhoto()).thenReturn(null);

        GameResponse responseActual = gameCore.insertGame(mockGameRequest);

        Assert.assertTrue(isSame(new GameResponseImpl(202,"No photo present",null), responseActual));
    }

    @Test
    public void test_processGame_whenAllDataArePresent(){
        GameResponse responseActual = gameCore.insertGame(mockGameRequest);

        Mockito.verify(mockEntityManager).persist(Matchers.any());
        Assert.assertTrue(isSame(new GameResponseImpl(200,"Success",null), responseActual));
    }

    @Test
    public void test_fetchGameByName_whenNameIsBlank(){
        Mockito.when(mockGameRequest.getName()).thenReturn("");

        GameResponse responseActual = gameCore.fetchGameByName(mockGameRequest);

        Assert.assertTrue(isSame(new GameResponseImpl(202,"Name is blank",null), responseActual));
    }

    @Test
    public void test_fetchGameByName_whenNameIsNotBlank(){
        GameEntity mockEntity = Mockito.mock(GameEntity.class);
        List mockList = Mockito.mock(List.class);
        Query mockQuery = Mockito.mock(Query.class);
        Mockito.when(mockQuery.setParameter(Matchers.anyString(),Matchers.anyString())).thenReturn(mockQuery);
        Mockito.when(mockQuery.getResultList()).thenReturn(mockList);
        Mockito.when(mockList.get(0)).thenReturn(mockEntity);
        Mockito.when(mockEntityManager.createQuery(Matchers.anyString())).thenReturn(mockQuery);

        GameResponse responseActual = gameCore.fetchGameByName(mockGameRequest);

        Assert.assertTrue(isSame(new GameResponseImpl(200,"Success",mockEntity), responseActual));
    }

    @Test
    public void test_updateGameByName_whenRequestIsInvalid(){
        Mockito.when(mockGameRequest.getName()).thenReturn("");

        GameResponse responseActual = gameCore.updateGameByName(mockGameRequest);

        Assert.assertFalse(isSame(new GameResponseImpl(200,"Success",null), responseActual));
    }

    @Test
    public void test_updateGameByName_whenRequestIsValid(){
        GameEntity mockEntity = Mockito.mock(GameEntity.class);
        List mockList = Mockito.mock(List.class);
        Query mockQuery = Mockito.mock(Query.class);
        Mockito.when(mockQuery.setParameter(Matchers.anyString(),Matchers.anyString())).thenReturn(mockQuery);
        Mockito.when(mockQuery.getResultList()).thenReturn(mockList);
        Mockito.when(mockList.get(0)).thenReturn(mockEntity);
        Mockito.when(mockEntityManager.createQuery(Matchers.anyString())).thenReturn(mockQuery);

        GameResponse responseActual = gameCore.updateGameByName(mockGameRequest);

        Mockito.verify(mockEntityManager, Mockito.times(2)).createQuery(Matchers.anyString());
        Mockito.verify(mockQuery, Mockito.times(5)).setParameter(Matchers.anyString(),Matchers.anyString());
        Mockito.verify(mockQuery).getResultList();
        Mockito.verify(mockList).get(0);
        Mockito.verify(mockQuery).executeUpdate();

        Assert.assertTrue(isSame(new GameResponseImpl(200,"Success",null), responseActual));
    }

    @After
    public void tearDown(){
        gameCore = null;
    }

    private boolean isSame(GameResponse response1, GameResponse response2){
        return (response1.getCode() == response2.getCode()) &&
               (response1.getMessage().equals(response2.getMessage())) &&
               (response1.getData() == response2.getData());
    }

}
