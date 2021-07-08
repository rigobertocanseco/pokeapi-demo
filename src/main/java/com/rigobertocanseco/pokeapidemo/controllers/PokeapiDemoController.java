package com.rigobertocanseco.pokeapidemo.controllers;

import com.rigobertocanseco.pokeapidemo.client.PokeAPIClient;
import com.rigobertocanseco.pokeapidemo.client.PokemonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class PokeapiDemoController {
    Logger logger = LoggerFactory.getLogger(PokeapiDemoController.class);

    @RequestMapping("/{pokemon}")
    public PokemonResponse index(@PathVariable String pokemon) throws IOException {
        logger.info("An INFO Message");
        PokeAPIClient pokeAPIClient = new PokeAPIClient();
        try {
            PokemonResponse response = pokeAPIClient.sendGet(pokemon);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pokeAPIClient.close();
        }

        return null;
    }
}
