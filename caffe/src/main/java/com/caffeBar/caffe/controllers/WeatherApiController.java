package com.caffeBar.caffe.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/v1/weather")
public class WeatherApiController {
    @GetMapping
    public ResponseEntity<String> getWeatherApiResponse() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://weatherapi-com.p.rapidapi.com/current.json?q=44.086705%2C%2015.272212"))
                .header("X-RapidAPI-Key", "c453b958d0msh2ce2321b208f6cdp19fc33jsn017f76fe969d")
                .header("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com")
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());
        return ResponseEntity.ok().body(response.body());
    }
}
