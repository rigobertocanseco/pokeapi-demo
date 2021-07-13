package com.rigobertocanseco.pokeapidemo.ws;

import com.rigobertocanseco.pokeapidemo.client.entity.Ability;
import com.rigobertocanseco.pokeapidemo.client.PokeAPIClient;
import com.rigobertocanseco.pokeapidemo.client.entity.PokemonResponse;
import dev.rigobertocanseco.pokeapi.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.math.BigInteger;

@Endpoint
public class PokeapiEndpoint {
    protected static Logger logger = LoggerFactory.getLogger(PokeapiEndpoint.class);
    private static final String NAMESPACE_URI = "http://rigobertocanseco.dev/pokeapi";

    private PokemonResponse getPokemon(String name) {
        PokeAPIClient pokeAPIClient = new PokeAPIClient();
        PokemonResponse response = new PokemonResponse();

        try {
            response = pokeAPIClient.sendGet(name);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pokeAPIClient.close();
        }

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetPokemonRequest")
    @ResponsePayload
    public GetPokemonResponse getPokemon(@RequestPayload GetPokemonRequest request) {
        logger.info("REQUEST WS: GetPokemonRequest" + "/" + request.getName());
        GetPokemonResponse getPokemonResponse = new GetPokemonResponse();
        PokemonResponse pokemonResponse = getPokemon(request.getName());
        if(pokemonResponse == null)
            return getPokemonResponse;
        else {
            Pokemon pokemon = new Pokemon();
            pokemon.setName(pokemonResponse.getName());
            pokemon.setBaseExperience(BigInteger.valueOf(pokemonResponse.getBaseExperience()));
            pokemon.setId(BigInteger.valueOf(pokemonResponse.getId()));
            pokemon.setLocationAreaEncounters(pokemonResponse.getLocationAreaEncounters());

            for (com.rigobertocanseco.pokeapidemo.client.entity.Held held : pokemonResponse.getHeldItems()) {
                Held h = new Held();
                HeldItem heldItem = new HeldItem();
                heldItem.setName(held.getItem().getName());
                heldItem.setUrl(held.getItem().getUrl());
                h.setItem(heldItem);
                for (com.rigobertocanseco.pokeapidemo.client.entity.VersionDetail version : held.getVersionDetails()) {
                    VersionDetail versionDetail = new VersionDetail();
                    Version v = new Version();
                    v.setName(version.getVersion().getName());
                    v.setUrl(version.getVersion().getUrl());
                    versionDetail.setVersion(v);
                    versionDetail.setRarity(version.getRarity());
                    h.getVersionDetails().add(versionDetail);
                }
                pokemon.getHeldItems().add(h);
            }

            for (Ability ability : pokemonResponse.getAbilities()) {
                dev.rigobertocanseco.pokeapi.Ability a = new dev.rigobertocanseco.pokeapi.Ability();
                a.setSlot(ability.getSlot());
                a.setIsHidden(ability.getHidden());
                AbilityObject abilityObject = new AbilityObject();
                abilityObject.setName(ability.getAbility().getName());
                abilityObject.setUrl(ability.getAbility().getUrl());
                a.setAbility(abilityObject);
                pokemon.getAbilities().add(a);
            }
            getPokemonResponse.setPokemon(pokemon);
            logger.info("RESPONSE WS: " + pokemonResponse);

            return getPokemonResponse;
        }
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAbilitiesRequest")
    @ResponsePayload
    public GetPokemonResponse getAbilities(@RequestPayload GetAbilitiesRequest request) {
        logger.info("REQUEST WS: GetAbilitiesRequest" + "/" + request.getName());
        GetPokemonResponse getPokemonResponse = new GetPokemonResponse();
        PokemonResponse pokemonResponse = getPokemon(request.getName());

        if(pokemonResponse == null)
            return getPokemonResponse;
        else {
            Pokemon pokemon = new Pokemon();
            for (Ability ability : pokemonResponse.getAbilities()) {
                dev.rigobertocanseco.pokeapi.Ability a = new dev.rigobertocanseco.pokeapi.Ability();
                a.setSlot(ability.getSlot());
                a.setIsHidden(ability.getHidden());
                AbilityObject abilityObject = new AbilityObject();
                abilityObject.setName(ability.getAbility().getName());
                abilityObject.setUrl(ability.getAbility().getUrl());
                a.setAbility(abilityObject);
                pokemon.getAbilities().add(a);
            }
            GetPokemonResponse response = new GetPokemonResponse();
            response.setPokemon(pokemon);
            logger.info("RESPONSE WS: " + pokemonResponse);

            return response;
        }
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetBaseExperienceRequest")
    @ResponsePayload
    public GetPokemonResponse getBaseExperience(@RequestPayload GetBaseExperienceRequest request) {
        logger.info("REQUEST WS: GetBaseExperienceRequest" + "/" + request.getName());
        GetPokemonResponse getPokemonResponse = new GetPokemonResponse();
        PokemonResponse pokemonResponse = getPokemon(request.getName());

        if(pokemonResponse == null)
            return getPokemonResponse;
        else {
            Pokemon pokemon = new Pokemon();
            pokemon.setBaseExperience(BigInteger.valueOf(pokemonResponse.getBaseExperience()));
            GetPokemonResponse response = new GetPokemonResponse();
            response.setPokemon(pokemon);
            logger.info("RESPONSE WS: " + pokemonResponse);
            return response;
        }
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetHeldItemsRequest")
    @ResponsePayload
    public GetPokemonResponse getHeldItems(@RequestPayload GetHeldItemsRequest request) {
        logger.info("REQUEST WS: GetHeldItemsRequest" + "/" + request.getName());
        GetPokemonResponse getPokemonResponse = new GetPokemonResponse();
        PokemonResponse pokemonResponse = getPokemon(request.getName());

        if(pokemonResponse == null)
            return getPokemonResponse;
        else {
            Pokemon pokemon = new Pokemon();

            for (com.rigobertocanseco.pokeapidemo.client.entity.Held held : pokemonResponse.getHeldItems()) {
                Held h = new Held();
                HeldItem heldItem = new HeldItem();
                heldItem.setName(held.getItem().getName());
                heldItem.setUrl(held.getItem().getUrl());
                h.setItem(heldItem);
                for (com.rigobertocanseco.pokeapidemo.client.entity.VersionDetail version : held.getVersionDetails()) {
                    VersionDetail versionDetail = new VersionDetail();
                    Version v = new Version();
                    v.setName(version.getVersion().getName());
                    v.setUrl(version.getVersion().getUrl());
                    versionDetail.setVersion(v);
                    versionDetail.setRarity(version.getRarity());
                    h.getVersionDetails().add(versionDetail);
                }
                pokemon.getHeldItems().add(h);
            }

            GetPokemonResponse response = new GetPokemonResponse();
            response.setPokemon(pokemon);
            logger.info("RESPONSE WS: " + pokemonResponse);

            return response;
        }
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetIdRequest")
    @ResponsePayload
    public GetPokemonResponse getId(@RequestPayload GetIdRequest request) {
        logger.info("REQUEST WS: GetIdRequest" + "/" + request.getName());
        GetPokemonResponse getPokemonResponse = new GetPokemonResponse();
        PokemonResponse pokemonResponse = getPokemon(request.getName());

        if(pokemonResponse == null)
            return getPokemonResponse;
        else {
            Pokemon pokemon = new Pokemon();
            pokemon.setId(BigInteger.valueOf(pokemonResponse.getId()));
            GetPokemonResponse response = new GetPokemonResponse();
            response.setPokemon(pokemon);
            logger.info("RESPONSE WS: " + pokemonResponse);

            return response;
        }
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetNameRequest")
    @ResponsePayload
    public GetPokemonResponse getName(@RequestPayload GetNameRequest request) {
        logger.info("REQUEST WS: GetNameRequest" + "/" + request.getName());
        GetPokemonResponse getPokemonResponse = new GetPokemonResponse();
        PokemonResponse pokemonResponse = getPokemon(request.getName());
        if(pokemonResponse == null)
            return getPokemonResponse;
        else {
            Pokemon pokemon = new Pokemon();
            pokemon.setName(pokemonResponse.getName());
            GetPokemonResponse response = new GetPokemonResponse();
            response.setPokemon(pokemon);
            logger.info("RESPONSE WS: " + pokemonResponse);

            return response;
        }
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetLocationAreaEncountersRequest")
    @ResponsePayload
    public GetPokemonResponse getLocationAreaEncounters(@RequestPayload GetLocationAreaEncountersRequest request) {
        logger.info("REQUEST WS: GetLocationAreaEncountersRequest" + "/" + request.getName());
        GetPokemonResponse getPokemonResponse = new GetPokemonResponse();
        PokemonResponse pokemonResponse = getPokemon(request.getName());

        if(pokemonResponse == null)
            return getPokemonResponse;
        else {
            Pokemon pokemon = new Pokemon();
            pokemon.setLocationAreaEncounters(pokemonResponse.getLocationAreaEncounters());
            GetPokemonResponse response = new GetPokemonResponse();
            response.setPokemon(pokemon);
            logger.info("RESPONSE WS: " + pokemonResponse);

            return response;
        }
    }

}
