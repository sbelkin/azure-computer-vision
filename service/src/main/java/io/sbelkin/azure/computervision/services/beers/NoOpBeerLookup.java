package io.sbelkin.azure.computervision.services.beers;

import io.sbelkin.azure.computervision.models.Beer;

import java.util.ArrayList;
import java.util.List;

public class NoOpBeerLookup implements BeerLookup {

    @Override
    public List<Beer> lookUpName(String name) {
        return new ArrayList<>();
    }
}
