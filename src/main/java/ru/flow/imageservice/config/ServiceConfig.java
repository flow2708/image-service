package ru.flow.imageservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import ru.flow.imageservice.service.ImageDownloadService;

@Configuration
public class ServiceConfig {
    public final RestClient restClient;

    public ServiceConfig(RestClient restClient) {
        this.restClient = restClient;
    }
    @Bean
    public ImageDownloadService imageDownloadService() {
        return new ImageDownloadService(restClient);
    }
}
