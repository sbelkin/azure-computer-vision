package io.sbelkin.azure.computervision.configurations;

import io.sbelkin.azure.computervision.exceptions.ConfigurationException;
import io.sbelkin.azure.computervision.services.extractions.BasicMenuExtraction;
import io.sbelkin.azure.computervision.services.extractions.MenuExtraction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MenuExtractionConfiguration {

    public enum MenuExtractionEnum {
        BASIC, UNKNOWN
    }

    @Value("${menu.extraction.type}")
    private MenuExtractionEnum menuExtraction;

    @Bean
    public MenuExtraction menuExtraction() throws ConfigurationException {
        switch (menuExtraction) {
            case BASIC:
                return new BasicMenuExtraction();
            case UNKNOWN:
            default:
                throw new ConfigurationException("Failure to configure menu extraction: " + menuExtraction);
        }
    }

}
