package com.anup.gamewebservice;

import com.anup.gamewebservice.resource.GameResource;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class GameApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(GameResource.class);
        classes.add(MultiPartFeature.class);
        return classes;
    }

}
