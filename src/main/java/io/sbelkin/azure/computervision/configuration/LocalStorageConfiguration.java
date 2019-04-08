package io.sbelkin.azure.computervision.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("storage.service.local")
public class LocalStorageConfiguration {

    public String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "LocalStorageConfiguration{" +
                "filePath='" + filePath + '\'' +
                '}';
    }
}
