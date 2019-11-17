package io.sbelkin.azure.computervision.services.beers;

import io.sbelkin.azure.computervision.models.Beer;

import java.util.List;

public interface BeerLookup {

    List<Beer> lookUpName(String name);
}
