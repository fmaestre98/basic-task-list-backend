package com.basictasklist.basictasklist.config;

import com.basictasklist.basictasklist.entities.Task;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private String allowedOrigin="https://basic-task-list-frontend.vercel.app/";

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors){

        config.exposeIdsFor(Task.class);

         //Configure CORS
        cors.addMapping(config.getBasePath() + "/**").allowedOrigins(allowedOrigin);
    }

}
