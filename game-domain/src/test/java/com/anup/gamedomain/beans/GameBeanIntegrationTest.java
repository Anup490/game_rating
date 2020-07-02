package com.anup.gamedomain.beans;

import com.anup.gamecore.core.GameCore;
import com.anup.gamecore.core.GameCoreImpl;
import com.anup.gamecore.dto.GameRequest;
import com.anup.gamecore.dto.GameResponse;
import com.anup.gamecore.persistance.GamePersistor;
import com.anup.gamecore.validation.GameValidator;
import com.anup.gamecore.validation.GameValidatorImpl;
import com.anup.gamecore.validation.ValidationStatus;
import com.anup.gamecore.utils.StringUtils;
import com.anup.gamedomain.persistance.GameEntity;
import com.anup.gamedomain.persistance.JPAPersistor;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

@RunWith(Arquillian.class)
public class GameBeanIntegrationTest {

    @Deployment
    public static JavaArchive createArchive(){
        return ShrinkWrap.create(JavaArchive.class, "game-domain.jar")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addClasses(GameCore.class, GameCoreImpl.class, GameRequest.class, GameResponse.class
                        , GamePersistor.class, GameValidator.class, GameValidatorImpl.class, ValidationStatus.class
                        , GameBean.class, JPAPersistor.class, GameEntity.class, StringUtils.class);
    }

    @EJB
    private GameBean gameBean;

    @Test
    public void test_insertGameData(){
        GameRequest request = prepareRequest();

        GameResponse responseActual = gameBean.insertGameData(request);

        Assert.assertTrue(isSame(new GameResponse(200,"Success",null),responseActual));
    }

    @Test
    public void test_updateGameData(){
        GameRequest request = prepareRequest();
        GameResponse responseActualFetch = gameBean.fetchGameDataByName(request);

        Assert.assertNotNull(responseActualFetch.getData());

        final GameEntity entity = (GameEntity) responseActualFetch.getData();
        entity.setDescription("yo ho naya description");

        GameRequest updateRequest = new GameRequest.Builder()
                .setName(entity.getName())
                .setDescription("yo ho naya description")
                .setRating(""+entity.getRating())
                .setPhoto(entity.getPhoto())
                .build();

        GameResponse responseActualUpdate = gameBean.updateGameDataByName(updateRequest);

        Assert.assertTrue(isSame(new GameResponse(200,"Success",null),responseActualUpdate));
    }

    private boolean isSame(GameResponse response1, GameResponse response2){
        return (response1.getCode() == response2.getCode()) &&
                (response1.getMessage().equals(response2.getMessage())) &&
                (response1.getData() == response2.getData());
    }

    private GameRequest prepareRequest(){
        return new GameRequest.Builder()
                .setName("Anup Raj")
                .setDescription("arko description")
                .setRating("99")
                .setPhoto("new byte[1]".getBytes())
                .build();
    }

}
