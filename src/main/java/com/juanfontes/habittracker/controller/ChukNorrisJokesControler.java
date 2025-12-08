package com.juanfontes.habittracker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juanfontes.habittracker.model.ChuckNorrisJokesResponse;
import com.juanfontes.habittracker.service.ChukNorrisJokesService;

@RestController
@RequestMapping("/api/jokes")
public class ChukNorrisJokesControler {

    private ChukNorrisJokesService service;

    public ChukNorrisJokesControler(ChukNorrisJokesService servicio){
        this.service = servicio;
    }

    @GetMapping("/joke")
    public ChuckNorrisJokesResponse getJoke(){
        return service.getJoke();  
    }

}
