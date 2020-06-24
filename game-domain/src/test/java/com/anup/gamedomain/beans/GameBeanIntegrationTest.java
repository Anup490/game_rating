package com.anup.gamedomain.beans;

import com.anup.gamedomain.api.GameBean;
import com.anup.gamedomain.api.GameRequest;
import com.anup.gamedomain.api.GameResponse;
import com.anup.gamedomain.utils.StringUtils;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.io.InputStream;

@RunWith(Arquillian.class)
public class GameBeanIntegrationTest {

    @Deployment
    public static JavaArchive createArchive(){
        return ShrinkWrap.create(JavaArchive.class, "game-domain.jar")
                .addClasses(GameBean.class,GameBeanImpl.class, GameResponseImpl.class, StringUtils.class);
    }

    @EJB
    private GameBean gameBean;

    @Test
    public void test_gameBeanInjection(){
        GameResponse response = gameBean.processGameData(prepareRequest());
        System.out.println("Code : "+response.getCode());
        System.out.println("Message : "+response.getMessage());
    }

    private GameRequest prepareRequest(){
        return new GameRequest() {
            public String getName() {
                return "Anup";
            }

            public String getDescription() {
                return "description";
            }

            public int getRating() {
                return 80;
            }

            public InputStream getPhoto() {
                return null;
            }

            public byte[] getBuffer() {
                return new byte[0];
            }
        };
    }

}
