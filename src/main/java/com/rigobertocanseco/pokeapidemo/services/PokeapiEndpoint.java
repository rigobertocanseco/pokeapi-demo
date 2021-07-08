package com.rigobertocanseco.pokeapidemo.services;

import com.rigobertocanseco.pokeapidemo.client.PokeAPIClient;
import com.rigobertocanseco.pokeapidemo.client.PokemonResponse;
import dev.rigobertocanseco.pokeapi.GetPokemonRequest;
import dev.rigobertocanseco.pokeapi.GetPokemonResponse;
import dev.rigobertocanseco.pokeapi.Pokemon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.io.IOException;

@Endpoint
public class PokeapiEndpoint {

    protected static Logger logger = LoggerFactory.getLogger(PokeapiEndpoint.class);
    private static final String NAMESPACE_URI = "http://rigobertocanseco.dev/pokeapi";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPokemonRequest")
    @ResponsePayload
    public GetPokemonResponse getPokemon(@RequestPayload GetPokemonRequest request) throws IOException {
        logger.info("getPokemon");
        GetPokemonResponse getPokemonResponse = new GetPokemonResponse();
        PokeAPIClient pokeAPIClient = new PokeAPIClient();
        try {
            PokemonResponse response = pokeAPIClient.sendGet("bulbasaur");

            Pokemon pokemon = new Pokemon();
            pokemon.setName(response.getName());
            getPokemonResponse.setPokemon(pokemon);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pokeAPIClient.close();
        }

        return getPokemonResponse;
    }

}
