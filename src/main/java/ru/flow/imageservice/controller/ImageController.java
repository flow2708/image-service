package ru.flow.imageservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
public class ImageController {
    private final RestClient restClient;

    public ImageController(RestClient restClient) {
        this.restClient = restClient;
    }

    @GetMapping("/image")
    public byte[] image(@RequestParam String url) {

    }
}
