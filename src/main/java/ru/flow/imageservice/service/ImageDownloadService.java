package ru.flow.imageservice.service;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;
import ru.flow.imageservice.dto.DownloadedImage;
import ru.flow.imageservice.exception.InvalidUrlException;

import java.net.URISyntaxException;

public class ImageDownloadService {
    private final RestClient restClient;

    public ImageDownloadService(RestClient restClient) {
        this.restClient = restClient;
    }
    public DownloadedImage download(String url) {
        if (url == null || url.isBlank()) {
            throw new InvalidUrlException("The URL cannot be empty.");
        }
        ResponseEntity<byte[]> response = restClient.get()
                .uri(url)
                .retrieve()
                .toEntity(byte[].class);
        byte[] data = response.getBody();
        MediaType contentType = response.getHeaders().getContentType();

        if (contentType == null) {
            contentType = MediaType.APPLICATION_OCTET_STREAM;
        }

        return new DownloadedImage(data, contentType);
    }
}
