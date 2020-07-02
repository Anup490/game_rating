package com.anup.gamecore.validation;

import com.anup.gamecore.dto.GameRequest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class GameValidatorImplUnitTest {

    @Mock GameRequest mockGameRequest;

    private GameValidatorImpl gameValidator;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        Mockito.when(mockGameRequest.getName()).thenReturn("Anup");
        Mockito.when(mockGameRequest.getDescription()).thenReturn("description");
        Mockito.when(mockGameRequest.getRating()).thenReturn(99);
        Mockito.when(mockGameRequest.getPhoto()).thenReturn(new byte[1]);

        gameValidator = new GameValidatorImpl();
    }

    @Test
    public void test_validate_whenNameIsBlank(){
        Mockito.when(mockGameRequest.getName()).thenReturn("");

        ValidationStatus status = gameValidator.validate(mockGameRequest);

        Assert.assertEquals(ValidationStatus.NO_NAME,status);
    }

    @Test
    public void test_validate_whenDescriptionIsBlank(){
        Mockito.when(mockGameRequest.getDescription()).thenReturn("");

        ValidationStatus status = gameValidator.validate(mockGameRequest);

        Assert.assertEquals(ValidationStatus.NO_DESCRIPTION,status);
    }

    @Test
    public void test_validate_whenRatingIsMinus1(){
        Mockito.when(mockGameRequest.getRating()).thenReturn(-1);

        ValidationStatus status = gameValidator.validate(mockGameRequest);

        Assert.assertEquals(ValidationStatus.NO_RATING,status);
    }

    @Test
    public void test_validate_whenPhotoIsEmpty(){
        Mockito.when(mockGameRequest.getPhoto()).thenReturn(null);

        ValidationStatus status = gameValidator.validate(mockGameRequest);

        Assert.assertEquals(ValidationStatus.NO_PHOTO,status);
    }

    @Test
    public void test_validate_whenRequestIsValid(){
        ValidationStatus status = gameValidator.validate(mockGameRequest);

        Assert.assertEquals(ValidationStatus.OK,status);
    }

    @After
    public void tearDown(){
        gameValidator = null;
    }

}
