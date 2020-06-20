package com.anup.gamedomain.beans;

import com.anup.gamedomain.api.GameRequest;
import com.anup.gamedomain.api.GameResponse;
import com.anup.gamedomain.utils.StringUtils;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.io.InputStream;

@LocalBean
@Stateless
public class GameBean {

    public GameResponse processGameData(GameRequest game){
        if(StringUtils.isBlank(game.getName())){
            return new GameResponseImpl(202,"Name is blank",null);
        }else if(StringUtils.isBlank(game.getDescription())){
            return new GameResponseImpl(202,"Description is blank",null);
        }else if(game.getRating() == -1){
            return new GameResponseImpl(202,"Rating is blank",null);
        }else if(hasNoContent(game.getPhoto())){
            return new GameResponseImpl(202,"No file present",null);
        }else{
            return new GameResponseImpl(200,"Success",null);
        }
    }

    private boolean hasNoContent(InputStream inputStream){
        try {
            byte[] content = new byte[1024];
            return inputStream.read(content) == -1;
        } catch (Exception e) {
            return true;
        }
    }

}
