package ru.flow.imageservice.service;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;
import ru.flow.imageservice.dto.DownloadedImage;
import ru.flow.imageservice.exception.*;

import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class ImageDownloadService {
    private final RestClient restClient;

    public ImageDownloadService(RestClient restClient) {
        this.restClient = restClient;
    }
    public DownloadedImage download(String url) {
        if (url == null || url.isBlank()) {
            throw new InvalidUrlException("The URL cannot be empty.");
        }
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            throw new InvalidUrlException("URL must start with http:// or https://");
        }

        try {
            new URI(url);
        } catch (URISyntaxException e) {
            throw new InvalidUrlException("URI syntax error");
        }

        try {
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
        } catch (IllegalArgumentException e) {
            throw new InvalidUrlException("Invalid URL: " + url);
        }
        catch (ResourceAccessException e) {
            if (e.getCause() instanceof SocketTimeoutException) {
                throw new ImageSourceTimeoutException("Image source timeout: " + url);
            }
            throw new NetworkErrorException("Network error: " + e.getMessage());
        }
        catch (HttpClientErrorException | HttpServerErrorException e) {
            if (e instanceof HttpServerErrorException) {
                throw new HttpServerException("Http server error: " + ((HttpServerErrorException) e).getMessage());
            }
            throw new HttpClientException("Http client error: " + ((HttpClientErrorException) e).getMessage());
        }
    }
}
