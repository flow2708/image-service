package ru.flow.imageservice.dto;

import org.springframework.http.MediaType;

public record DownloadedImage(byte[] data, MediaType contentType) {
}
