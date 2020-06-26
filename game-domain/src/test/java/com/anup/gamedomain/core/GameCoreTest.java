package com.anup.gamedomain.core;

import com.anup.gamedomain.api.GameRequest;
import com.anup.gamedomain.api.GameResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;

public class GameCoreTest {

    @Mock EntityManager mockEntityManager;
    @Mock GameRequest mockGameRequest;

    private GameCore gameCore;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        gameCore = new GameCore(mockEntityManager);

        Mockito.when(mockGameRequest.getName()).thenReturn("Anup");
        Mockito.when(mockGameRequest.getDescription()).thenReturn("description");
        Mockito.when(mockGameRequest.getRating()).thenReturn(99);
        Mockito.when(mockGameRequest.getPhoto()).thenReturn(new byte[1]);
    }

    @Test
    public void test_processGame_whenNameIsBlank(){
        Mockito.when(mockGameRequest.getName()).thenReturn("");

        GameResponse responseActual = gameCore.processGame(mockGameRequest);

        Assert.assertTrue(isSame(new GameResponseImpl(202,"Name is blank",null), responseActual));
    }

    @Test
    public void test_processGame_whenDescriptionIsBlank(){
        Mockito.when(mockGameRequest.getDescription()).thenReturn("");

        GameResponse responseActual = gameCore.processGame(mockGameRequest);

        Assert.assertTrue(isSame(new GameResponseImpl(202,"Description is blank",null), responseActual));
    }

    @Test
    public void test_processGame_whenRatingIsMinusOne(){
        Mockito.when(mockGameRequest.getRating()).thenReturn(-1);

        GameResponse responseActual = gameCore.processGame(mockGameRequest);

        Assert.assertTrue(isSame(new GameResponseImpl(202,"Rating is blank",null), responseActual));
    }

    @Test
    public void test_processGame_whenFileHasNoContent(){
        Mockito.when(mockGameRequest.getPhoto()).thenReturn(null);

        GameResponse responseActual = gameCore.processGame(mockGameRequest);

        Assert.assertTrue(isSame(new GameResponseImpl(202,"No file present",null), responseActual));
    }

    @Test
    public void test_processGame_whenExceptionOccursOnReadingFile(){
        Mockito.when(mockGameRequest.getPhoto()).thenReturn(null);

        GameResponse responseActual = gameCore.processGame(mockGameRequest);

        Assert.assertTrue(isSame(new GameResponseImpl(202,"No file present",null), responseActual));
    }

    @Test
    public void test_processGame_whenAllDataArePresent(){
        GameResponse responseActual = gameCore.processGame(mockGameRequest);

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
