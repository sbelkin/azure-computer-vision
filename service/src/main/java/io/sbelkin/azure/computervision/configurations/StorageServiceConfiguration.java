package io.sbelkin.azure.computervision.configurations;

import io.sbelkin.azure.computervision.exceptions.ConfigurationException;
import io.sbelkin.azure.computervision.services.storage.AzureStorageService;
import io.sbelkin.azure.computervision.services.storage.LocalFileStorageService;
import io.sbelkin.azure.computervision.services.storage.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageServiceConfiguration {

    public enum StorageServiceEnum {
        LOCAL_STORAGE, AZURE_STORAGE, UNKNOWN
    }

    @Value("${storage.service.type}")
    private StorageServiceEnum storageService;

    @Bean
    public StorageService storageService() throws ConfigurationException {
        switch (storageService) {
            case AZURE_STORAGE:
                return new AzureStorageService();
            case LOCAL_STORAGE:
                return new LocalFileStorageService();
            case UNKNOWN:
            default:
                throw new ConfigurationException("Failure to configure storage service: " + storageService);
        }
    }

}
