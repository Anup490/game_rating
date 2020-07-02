package com.anup.gamecore.core;

import com.anup.gamecore.dto.GameRequest;
import com.anup.gamecore.dto.GameResponse;
import com.anup.gamecore.persistance.GamePersistor;
import com.anup.gamecore.validation.GameValidatorImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class GameCoreImplUnitTest {

    @Mock GameRequest mockGameRequest;
    @Mock GamePersistor mockPersistor;
    private GameCoreImpl gameCore;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        Mockito.when(mockGameRequest.getName()).thenReturn("Anup");
        Mockito.when(mockGameRequest.getDescription()).thenReturn("description");
        Mockito.when(mockGameRequest.getRating()).thenReturn(99);
        Mockito.when(mockGameRequest.getPhoto()).thenReturn(new byte[1]);

        gameCore = new GameCoreImpl(mockPersistor, new GameValidatorImpl());
    }

    @Test
    public void test_processGame_whenNameIsBlank(){
        Mockito.when(mockGameRequest.getName()).thenReturn("");

        GameResponse responseActual = gameCore.insertGame(mockGameRequest);

        Assert.assertTrue(isSame(new GameResponse(202,"Name is blank",null), responseActual));
    }

    @Test
    public void test_processGame_whenDescriptionIsBlank(){
        Mockito.when(mockGameRequest.getDescription()).thenReturn("");

        GameResponse responseActual = gameCore.insertGame(mockGameRequest);

        Assert.assertTrue(isSame(new GameResponse(202,"Description is blank",null), responseActual));
    }

    @Test
    public void test_processGame_whenRatingIsMinusOne(){
        Mockito.when(mockGameRequest.getRating()).thenReturn(-1);

        GameResponse responseActual = gameCore.insertGame(mockGameRequest);

        Assert.assertTrue(isSame(new GameResponse(202,"Rating is blank",null), responseActual));
    }

    @Test
    public void test_processGame_whenPhotoIsMissing(){
        Mockito.when(mockGameRequest.getPhoto()).thenReturn(null);

        GameResponse responseActual = gameCore.insertGame(mockGameRequest);

        Assert.assertTrue(isSame(new GameResponse(202,"No photo present",null), responseActual));
    }

    @Test
    public void test_processGame_whenAllDataArePresent(){
        GameResponse responseActual = gameCore.insertGame(mockGameRequest);

        Mockito.verify(mockPersistor).persistGame(mockGameRequest);
        Assert.assertTrue(isSame(new GameResponse(200,"Success",null), responseActual));
    }

    @Test
    public void test_fetchGameByName_whenNameIsBlank(){
        Mockito.when(mockGameRequest.getName()).thenReturn("");

        GameResponse responseActual = gameCore.fetchGameByName(mockGameRequest);

        Assert.assertTrue(isSame(new GameResponse(202,"Name is blank",null), responseActual));
    }

    @Test
    public void test_fetchGameByName_whenNameIsNotBlank(){
        Object mockObject = Mockito.mock(Object.class);
        Mockito.when(mockPersistor.fetchGameByName(Matchers.anyString())).thenReturn(mockObject);

        GameResponse responseActual = gameCore.fetchGameByName(mockGameRequest);

        Mockito.verify(mockPersistor).fetchGameByName("Anup");
        Assert.assertTrue(isSame(new GameResponse(200,"Success",mockObject), responseActual));
    }

    @Test
    public void test_updateGameByName_whenRequestIsInvalid(){
        Mockito.when(mockGameRequest.getName()).thenReturn("");

        GameResponse responseActual = gameCore.updateGameByName(mockGameRequest);

        Assert.assertFalse(isSame(new GameResponse(200,"Success",null), responseActual));
    }

    @Test
    public void test_updateGameByName_whenRequestIsValid(){
        GameResponse responseActual = gameCore.updateGameByName(mockGameRequest);

        Mockito.verify(mockPersistor).updateGameByName(mockGameRequest);
        Assert.assertTrue(isSame(new GameResponse(200,"Success",null), responseActual));
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
