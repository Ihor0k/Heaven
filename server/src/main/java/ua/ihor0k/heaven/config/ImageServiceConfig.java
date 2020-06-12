package ua.ihor0k.heaven.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import ua.ihor0k.heaven.service.ImageService;
import ua.ihor0k.heaven.service.LocalImageService;

@Configuration
public class ImageServiceConfig {
    @Value("${local.image.dir}")
    private String localImageDir;

    @Profile("local")
    @Bean
    public ImageService localImageService() {
        return new LocalImageService(localImageDir);
    }

    @Profile("prod")
    @Bean
    public ImageService s3ImageService() {
        return null;
    }
}
