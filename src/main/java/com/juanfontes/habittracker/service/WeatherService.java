package com.juanfontes.habittracker.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.juanfontes.habittracker.model.WeatherResponse;

@Service
public class WeatherService {

    private final WebClient webClient = WebClient.create();
    private final String API_KEY = "d44399c2e5b40393b3989eeeaddb2d96";

    public WeatherResponse getWeather(double lat, double lon) {

        String url = "https://api.openweathermap.org/data/2.5/weather"
                + "?lat=" + lat
                + "&lon=" + lon
                + "&appid=" + API_KEY
                + "&units=metric";

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(WeatherResponse.class)
                .block();
    }

}


