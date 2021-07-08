package com.rigobertocanseco.pokeapidemo;

import com.rigobertocanseco.pokeapidemo.client.PokeAPIClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class PokeapiDemoApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(PokeapiDemoApplication.class, args);
        PokeAPIClient pokeAPIClient = new PokeAPIClient();
        try {
            pokeAPIClient.sendGet("bulbasaur");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pokeAPIClient.close();
        }
    }

}
