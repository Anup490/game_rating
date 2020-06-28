package com.anup.gamewebservice.resource;

import com.anup.gamedomain.api.GameBean;
import com.anup.gamewebservice.dto.Response;
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

    @EJB private GameBean bean;
    private GameController controller;

    public GameResource(){
        controller = new GameController(bean);
    }

    @POST
    @Path("/insert")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertGameInfo(@FormDataParam("name") String name,
                                   @FormDataParam("desc") String desc,
                                   @FormDataParam("rating") String rating,
                                   @FormDataParam("photo") InputStream inputStream,
                                   @FormDataParam("photo") FormDataContentDisposition contentDisposition){
        return controller.insertGame(name,desc,rating,inputStream,contentDisposition);
    }

}
