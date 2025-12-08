package com.juanfontes.habittracker.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.juanfontes.habittracker.model.ChuckNorrisJokesResponse;

@Service
public class ChukNorrisJokesService {

    private String url = "https://api.chucknorris.io/jokes/random";
    private WebClient client = WebClient.create();

    public ChuckNorrisJokesResponse getJoke(){
        return client.get().uri(url).retrieve().bodyToMono(ChuckNorrisJokesResponse.class).block();
    }

}
