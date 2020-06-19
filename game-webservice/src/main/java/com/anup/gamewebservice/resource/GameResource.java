package com.anup.gamewebservice.resource;

import com.anup.gamedomain.api.Response;
import com.anup.gamedomain.beans.GameBean;
import com.anup.gamewebservice.dto.GameImpl;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;

@Path("/game")
public class GameResource {

    @EJB
    private GameBean bean;

    @POST
    @Path("/insert")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertGameInfo(@FormDataParam("name") String name,
                                   @FormDataParam("desc") String desc,
                                   @FormDataParam("rating") String rating,
                                   @FormDataParam("photo") InputStream inputStream,
                                   @FormDataParam("photo") FormDataContentDisposition contentDisposition){
        return bean.processGameData(new GameImpl.Builder()
                .setName(name)
                .setDescription(desc)
                .setRating(rating)
                .setPhoto(inputStream)
                .build());
    }

}
