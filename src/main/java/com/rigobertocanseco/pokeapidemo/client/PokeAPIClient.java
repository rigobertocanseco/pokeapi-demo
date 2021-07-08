package com.rigobertocanseco.pokeapidemo.client;

import com.google.gson.Gson;
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

    public void close() throws IOException {
        httpClient.close();
    }

    public PokemonResponse sendGet(String pokemon) throws Exception {
        HttpGet request = new HttpGet(URI + pokemon);

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            logger.info(response.getStatusLine().toString());

            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            logger.info(headers.toString());

            if (entity != null) {
                String result = EntityUtils.toString(entity);
                Gson gson = new Gson();
                PokemonResponse pokemonResponse = gson.fromJson(result, PokemonResponse.class);
                logger.info(pokemonResponse.getName());
                logger.info(pokemonResponse.getId().toString());
                logger.info(pokemonResponse.getBase_experience().toString());
                logger.info(pokemonResponse.getLocation_area_encounters());

                for (Ability ability:pokemonResponse.getAbilities()) {
                    logger.info(ability.toString());
                }

                for (Held held: pokemonResponse.getHeld_items()) {
                    for (VersionDetail version: held.getVersion_details()) {
                        logger.info(version.toString());
                    }
                    logger.info(held.getItem().toString());
                }

                return pokemonResponse;
            }
        }

        return null;
    }
}
