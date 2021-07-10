package com.rigobertocanseco.pokeapidemo.services;

import com.rigobertocanseco.pokeapidemo.client.Ability;
import com.rigobertocanseco.pokeapidemo.client.PokeAPIClient;
import com.rigobertocanseco.pokeapidemo.client.PokemonResponse;
import dev.rigobertocanseco.pokeapi.*;
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

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetPokemonRequest")
    @ResponsePayload
    public GetPokemonResponse getPokemon(@RequestPayload GetPokemonRequest request) throws IOException {
        logger.info("Request getPokemon");
        GetPokemonResponse getPokemonResponse = new GetPokemonResponse();
        PokeAPIClient pokeAPIClient = new PokeAPIClient();
        try {
            PokemonResponse response = pokeAPIClient.sendGet(request.getName());
            Pokemon pokemon = new Pokemon();
            pokemon.setName(response.getName());
            pokemon.setBaseExperience(response.getBase_experience());
            pokemon.setId(response.getId());
            pokemon.setLocationAreaEncounters(response.getLocation_area_encounters());

            for (Ability ability:response.getAbilities()) {
                dev.rigobertocanseco.pokeapi.Ability a = new dev.rigobertocanseco.pokeapi.Ability();
                a.setSlot(ability.getSlot());
                a.setIsHidden(ability.getHidden());
                AbilityObject abilityObject = new AbilityObject();
                abilityObject.setName(ability.getAbility().getName());
                abilityObject.setUrl(ability.getAbility().getUrl());
                a.setAbility(abilityObject);
                pokemon.getAbilities().add(a);
            }

            for (com.rigobertocanseco.pokeapidemo.client.Held held: response.getHeld_items()) {
                Held h = new Held();
                HeldItem heldItem = new HeldItem();
                heldItem.setName(held.getItem().getName());
                heldItem.setUrl(held.getItem().getUrl());
                h.setItem(heldItem);
                for (com.rigobertocanseco.pokeapidemo.client.VersionDetail version: held.getVersion_details()) {
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

            getPokemonResponse.setPokemon(pokemon);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pokeAPIClient.close();
        }

        return getPokemonResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAbilitiesRequest")
    @ResponsePayload
    public GetPokemonResponse getAbilities(@RequestPayload GetAbilitiesRequest request) {
        return null;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetBaseExperienceRequest")
    @ResponsePayload
    public GetPokemonResponse getBaseExperience(@RequestPayload GetBaseExperienceRequest request) {
        return null;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetHeldItemsRequest")
    @ResponsePayload
    public GetPokemonResponse getHeldItems(@RequestPayload GetHeldItemsRequest request) {
        return null;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetIdRequest")
    @ResponsePayload
    public GetPokemonResponse getId(@RequestPayload GetIdRequest request) {
        return null;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetNameRequest")
    @ResponsePayload
    public GetPokemonResponse getName(@RequestPayload GetNameRequest request) {
        return null;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetLocationAreaEncountersRequest")
    @ResponsePayload
    public GetPokemonResponse getLocationAreaEncounters(@RequestPayload GetLocationAreaEncountersRequest request) {
        return null;
    }


}
