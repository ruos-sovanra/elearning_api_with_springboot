package org.project.spring_mini_project.cofig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMvcConfiguration implements WebMvcConfigurer {
    @Value("${file_storage.image_location}")
    String fileStorageLocation;
    @Value("${file_storage.client_path}")
    String clientLocation;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(clientLocation)
                .addResourceLocations("file:"+fileStorageLocation);

    }

}
