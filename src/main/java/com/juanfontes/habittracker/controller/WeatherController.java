package com.juanfontes.habittracker.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.juanfontes.habittracker.model.WeatherResponse;
import com.juanfontes.habittracker.service.WeatherService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/weather")
public class WeatherController {
    private WeatherService weatherService;
    
    public WeatherController(WeatherService weatherService){
        this.weatherService = new WeatherService();
    }

    @GetMapping("/weatherNow")
    public WeatherResponse getWeather(@RequestParam double lat, @RequestParam double lon){
        return weatherService.getWeather(lat, lon);
    }

}
