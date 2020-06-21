package com.anup.gamedomain.beans;

import com.anup.gamedomain.api.GameBean;
import com.anup.gamedomain.api.GameRequest;
import com.anup.gamedomain.api.GameResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.io.InputStream;

public class GameBeanUnitTest {

    @Mock GameRequest mockGameRequest;
    @Mock InputStream mockInputStream;

    private byte[] buffer;
    private GameBean gameBean;

    @Before
    public void setup() throws IOException {
        MockitoAnnotations.initMocks(this);
        buffer = new byte[1];
        gameBean = new GameBeanImpl();

        Mockito.when(mockInputStream.read(buffer)).thenReturn(100);
        Mockito.when(mockGameRequest.getName()).thenReturn("Anup");
        Mockito.when(mockGameRequest.getDescription()).thenReturn("description");
        Mockito.when(mockGameRequest.getRating()).thenReturn(99);
        Mockito.when(mockGameRequest.getPhoto()).thenReturn(mockInputStream);
        Mockito.when(mockGameRequest.getBuffer()).thenReturn(buffer);
    }

    @Test
    public void test_processGameData_whenNameIsBlank(){
        Mockito.when(mockGameRequest.getName()).thenReturn("");

        GameResponse responseActual = gameBean.processGameData(mockGameRequest);

        Assert.assertTrue(isSame(new GameResponseImpl(202,"Name is blank",null), responseActual));
    }

    @Test
    public void test_processGameData_whenDescriptionIsBlank(){
        Mockito.when(mockGameRequest.getDescription()).thenReturn("");

        GameResponse responseActual = gameBean.processGameData(mockGameRequest);

        Assert.assertTrue(isSame(new GameResponseImpl(202,"Description is blank",null), responseActual));
    }

    @Test
    public void test_processGameData_whenRatingIsMinusOne(){
        Mockito.when(mockGameRequest.getRating()).thenReturn(-1);

        GameResponse responseActual = gameBean.processGameData(mockGameRequest);

        Assert.assertTrue(isSame(new GameResponseImpl(202,"Rating is blank",null), responseActual));
    }

    @Test
    public void test_processGameData_whenFileHasNoContent() throws IOException {
        Mockito.when(mockInputStream.read(buffer)).thenReturn(-1);

        GameResponse responseActual = gameBean.processGameData(mockGameRequest);

        Assert.assertTrue(isSame(new GameResponseImpl(202,"No file present",null), responseActual));
    }

    @Test
    public void test_processGameData_whenExceptionOccursOnReadingFile() throws IOException {
        Mockito.when(mockInputStream.read(buffer)).thenThrow(new IOException());

        GameResponse responseActual = gameBean.processGameData(mockGameRequest);

        Assert.assertTrue(isSame(new GameResponseImpl(202,"No file present",null), responseActual));
    }

    @Test
    public void test_processGameData_whenAllDataArePresent(){
        GameResponse responseActual = gameBean.processGameData(mockGameRequest);

        Assert.assertTrue(isSame(new GameResponseImpl(200,"Success",null), responseActual));
    }

    @After
    public void tearDown(){
        gameBean = null;
    }

    private boolean isSame(GameResponse response1, GameResponse response2){
        return (response1.getCode() == response2.getCode()) &&
               (response1.getMessage().equals(response2.getMessage())) &&
               (response1.getData() == response2.getData());
    }

}
