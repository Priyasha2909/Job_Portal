package com.code.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//this class will map requests for /photos to serve files from directory on our system

@Configuration
public class MvcConfig implements WebMvcConfigurer {
	//name of our upload directory
	 private static final String UPLOAD_DIR = "photos";

	    @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        exposeDirectory(UPLOAD_DIR, registry);
	    }

	    //setting path to upload directory
	    private void exposeDirectory(String uploadDir, ResourceHandlerRegistry registry) {
	        Path path = Paths.get(uploadDir);
	        // "/**" to match all the subdirectories
	        registry.addResourceHandler("/" + uploadDir + "/**").addResourceLocations("file:" + path.toAbsolutePath() + "/");
	    }
	
}
