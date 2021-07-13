package com.rigobertocanseco.pokeapidemo.client;

import com.google.gson.Gson;
import com.rigobertocanseco.pokeapidemo.client.entity.Ability;
import com.rigobertocanseco.pokeapidemo.client.entity.Held;
import com.rigobertocanseco.pokeapidemo.client.entity.PokemonResponse;
import com.rigobertocanseco.pokeapidemo.client.entity.VersionDetail;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;

public class PokeAPIClient {
    protected static Logger logger = LoggerFactory.getLogger(PokeAPIClient.class);
    public final String URI = "https://pokeapi.co/api/v2/pokemon/";
    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    public void close() {
        try {
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PokemonResponse sendGet(String pokemon) throws Exception {
        HttpGet request = new HttpGet(URI + pokemon);
        PokemonResponse pokemonResponse = new PokemonResponse();
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            Gson gson = new Gson();
            logger.info("REQUEST: " + request);
            logger.info("RESPONSE: " + response);
            if (response.getStatusLine().getStatusCode() == 200) {
                pokemonResponse = gson.fromJson(result, PokemonResponse.class);
                return pokemonResponse;
            }
        }

        return null;
    }
}
