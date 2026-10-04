package ru.flow.imageservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.flow.imageservice.dto.PingResponse;

@RestController
public class PingController {
    @GetMapping("/ping")
    public PingResponse ping() {
        return new PingResponse("ok");
    }
}
