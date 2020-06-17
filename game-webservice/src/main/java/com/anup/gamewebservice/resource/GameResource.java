package com.anup.gamewebservice.resource;

import com.anup.gamewebservice.dto.Response;
import com.anup.gamewebservice.utils.StringUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;

@Path("/game")
public class GameResource {

    @POST
    @Path("/insert")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertGameInfo(@FormDataParam("name") String name,
                                   @FormDataParam("desc") String desc,
                                   @FormDataParam("rating") String rating,
                                   @FormDataParam("photo") InputStream inputStream,
                                   @FormDataParam("photo") FormDataContentDisposition contentDisposition){
        if(StringUtils.isBlank(name)){
            return new Response(202,"Name is blank",null);
        }else if(StringUtils.isBlank(desc)){
            return new Response(202,"Description is blank",null);
        }else if(StringUtils.isBlank(rating)){
            return new Response(202,"Rating is blank",null);
        }else if(hasNoContent(inputStream)){
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
