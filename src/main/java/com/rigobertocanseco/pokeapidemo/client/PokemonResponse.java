package com.rigobertocanseco.pokeapidemo.client;

import java.util.ArrayList;

public class PokemonResponse {
    private ArrayList<Ability> abilities;
    private Integer base_experience;
    private ArrayList<Held> held_items;
    private Integer id;
    private String name;
    private String location_area_encounters;

    public PokemonResponse() {
    }

    public PokemonResponse(ArrayList<Ability> abilities, Integer base_experience, ArrayList<Held> held_items, Integer id, String name, String location_area_encounters) {
        this.abilities = abilities;
        this.base_experience = base_experience;
        this.held_items = held_items;
        this.id = id;
        this.name = name;
        this.location_area_encounters = location_area_encounters;
    }

    public ArrayList<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(ArrayList<Ability> abilities) {
        this.abilities = abilities;
    }

    public Integer getBase_experience() {
        return base_experience;
    }

    public void setBase_experience(Integer baseExperience) {
        this.base_experience = baseExperience;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Held> getHeld_items() {
        return held_items;
    }

    public void setHeld_items(ArrayList<Held> held_items) {
        this.held_items = held_items;
    }

    public String getLocation_area_encounters() {
        return location_area_encounters;
    }

    public void setLocation_area_encounters(String location_area_encounters) {
        this.location_area_encounters = location_area_encounters;
    }

    @Override
    public String toString() {
        return "PokemonResponse{" +
                "abilities=" + abilities +
                ", base_experience=" + base_experience +
                ", held_items=" + held_items +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", location_area_encounters='" + location_area_encounters + '\'' +
                '}';
    }
}
