package ru.flow.imageservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.flow.imageservice.dto.DownloadedImage;
import ru.flow.imageservice.service.ImageDownloadService;

@RestController
public class ImageController {
    private final ImageDownloadService imageDownloadService;

    public ImageController(ImageDownloadService imageDownloadService) {
        this.imageDownloadService = imageDownloadService;
    }

    @GetMapping("/image")
    public ResponseEntity<byte[]> image(@RequestParam String url) {
        DownloadedImage downloaded = imageDownloadService.download(url);
        return ResponseEntity.ok()
                .contentType(downloaded.contentType())
                .body(downloaded.data());
    }
}
