package com.anup.gamedomain.beans;

import com.anup.gamedomain.core.*;
import com.anup.gamedomain.api.GameRequest;
import com.anup.gamedomain.api.GameResponse;
import com.anup.gamedomain.enums.ValidationStatus;
import com.anup.gamedomain.utils.StringUtils;
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
                .addClasses(GameRequest.class, GameResponse.class, GameCore.class, GameBean.class, GameCoreImpl.class,
                        GameValidator.class, ValidationStatus.class, GameResponseImpl.class, GameEntity.class,
                        StringUtils.class);
    }

    @EJB
    private GameBean gameBean;

    @Test
    public void test_insertGameData(){
        GameRequest request = prepareRequest();

        GameResponse responseActual = gameBean.insertGameData(request);

        Assert.assertTrue(isSame(new GameResponseImpl(200,"Success",null),responseActual));
    }

    @Test
    public void test_updateGameData(){
        GameRequest request = prepareRequest();
        GameResponse responseActualFetch = gameBean.fetchGameDataByName(request);

        Assert.assertNotNull(responseActualFetch.getData());

        final GameEntity entity = (GameEntity) responseActualFetch.getData();
        entity.setDescription("yo ho naya description");

        GameRequest updateRequest = new GameRequest() {
            public String getName() {
                return entity.getName();
            }

            public String getDescription() {
                return "yo ho naya description";
            }

            public int getRating() {
                return entity.getRating();
            }

            public byte[] getPhoto() {
                return entity.getPhoto();
            }
        };

        GameResponse responseActualUpdate = gameBean.updateGameDataByName(updateRequest);

        Assert.assertTrue(isSame(new GameResponseImpl(200,"Success",null),responseActualUpdate));
    }

    private boolean isSame(GameResponse response1, GameResponse response2){
        return (response1.getCode() == response2.getCode()) &&
                (response1.getMessage().equals(response2.getMessage())) &&
                (response1.getData() == response2.getData());
    }

    private GameRequest prepareRequest(){
        return new GameRequest() {
            public String getName() {
                return "Anup Raj";
            }

            public String getDescription() {
                return "arko description";
            }

            public int getRating() {
                return 99;
            }

            public byte[] getPhoto() {
                return "new byte[1]".getBytes();
            }
        };
    }

}
