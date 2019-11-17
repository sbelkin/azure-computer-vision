package io.sbelkin.azure.computervision.services.storage.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("storage.service.azure")
public class AzureComputerVisionConfiguration {

    // **********************************************
    // *** Update or verify the following values. ***
    // **********************************************

    // You must use the same Azure region in your REST API method as you used to
    // get your subscription keys. For example, if you got your subscription keys
    // from the West US region, replace "westcentralus" in the URL
    // below with "westus".
    //
    // Free trial subscription keys are generated in the "westus" region.
    // If you use a free trial subscription key, you shouldn't need to change
    // this region.
    public String endpoint;
    public String subscriptionKey;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getSubscriptionKey() {
        return subscriptionKey;
    }

    public void setSubscriptionKey(String subscriptionKey) {
        this.subscriptionKey = subscriptionKey;
    }

    @Override
    public String toString() {
        return "AzureComputerVisionConfiguration{" +
                "endpoint='" + endpoint + '\'' +
                ", subscriptionKey='" + subscriptionKey + '\'' +
                '}';
    }
}
