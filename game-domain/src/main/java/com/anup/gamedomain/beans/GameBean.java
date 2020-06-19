package com.anup.gamedomain.beans;

import com.anup.gamedomain.api.Game;
import com.anup.gamedomain.api.Response;
import com.anup.gamedomain.utils.StringUtils;

import javax.ejb.LocalBean;
import java.io.InputStream;

@LocalBean
public class GameBean {

    public Response processGameData(Game game){
        if(StringUtils.isBlank(game.getName())){
            return new ResponseImpl(202,"Name is blank",null);
        }else if(StringUtils.isBlank(game.getDescription())){
            return new ResponseImpl(202,"Description is blank",null);
        }else if(game.getRating() == -1){
            return new ResponseImpl(202,"Rating is blank",null);
        }else if(hasNoContent(game.getPhoto())){
            return new ResponseImpl(202,"No file present",null);
        }else{
            return new ResponseImpl(200,"Success",null);
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
