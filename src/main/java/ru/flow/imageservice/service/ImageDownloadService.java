package ru.flow.imageservice.service;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;
import ru.flow.imageservice.dto.DownloadedImage;

public class ImageDownloadService {
    private final RestClient restClient;

    public ImageDownloadService(RestClient restClient) {
        this.restClient = restClient;
    }
    public DownloadedImage download(String url) {
        ResponseEntity<byte[]> response = restClient.get()
                .uri(url)
                .retrieve()
                .toEntity(byte[].class);
        byte[] data = response.getBody();
        MediaType contentType = response.getHeaders().getContentType();

        if(contentType == null) {
            contentType = MediaType.APPLICATION_OCTET_STREAM;
        }

        return new DownloadedImage(data, contentType);
    }
}
