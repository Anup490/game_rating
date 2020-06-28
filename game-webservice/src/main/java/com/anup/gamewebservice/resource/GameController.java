package com.anup.gamewebservice.resource;

import com.anup.gamedomain.api.GameBean;
import com.anup.gamewebservice.dto.GameRequestImpl;
import com.anup.gamewebservice.dto.Response;
import com.anup.gamewebservice.utils.FileUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.InputStream;

class GameController {

    private GameBean bean;

    GameController(GameBean bean){
        this.bean = bean;
    }

    Response insertGame(String name,
                        String desc,
                        String rating,
                        InputStream inputStream,
                        FormDataContentDisposition contentDisposition){
        return new Response(bean.processGameData(new GameRequestImpl.Builder()
                .setName(name)
                .setDescription(desc)
                .setRating(rating)
                .setPhoto(FileUtils.toBytes(inputStream))
                .build()));
    }

}
