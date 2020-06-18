package com.anup.gamedomain.beans;

import com.anup.gamedomain.dto.Game;
import com.anup.gamedomain.dto.Response;
import com.anup.gamedomain.utils.StringUtils;

import javax.ejb.LocalBean;
import java.io.InputStream;

@LocalBean
public class GameBean {

    public Response processGameData(Game game){
        if(StringUtils.isBlank(game.getName())){
            return new Response(202,"Name is blank",null);
        }else if(StringUtils.isBlank(game.getDescription())){
            return new Response(202,"Description is blank",null);
        }else if(game.getRating() == -1){
            return new Response(202,"Rating is blank",null);
        }else if(hasNoContent(game.getPhoto())){
            return new Response(202,"No file present",null);
        }else{
            return new Response(200,"Success",null);
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
