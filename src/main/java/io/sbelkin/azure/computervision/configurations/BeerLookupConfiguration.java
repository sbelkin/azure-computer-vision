package io.sbelkin.azure.computervision.configurations;

import io.sbelkin.azure.computervision.exceptions.ConfigurationException;
import io.sbelkin.azure.computervision.services.beers.BeerLookup;
import io.sbelkin.azure.computervision.services.beers.NoOpBeerLookup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeerLookupConfiguration {

    public enum BeerLookupEnum {
        NO_OP, UNKNOWN
    }

    @Value("${beer.lookup.type}")
    private BeerLookupEnum beerLookup;

    @Bean
    public BeerLookup beerLookup() throws ConfigurationException {
        switch (beerLookup) {
            case NO_OP:
                return new NoOpBeerLookup();
            case UNKNOWN:
            default:
                throw new ConfigurationException("Failure to configure beer lookup: "+beerLookup);
        }
    }

}
