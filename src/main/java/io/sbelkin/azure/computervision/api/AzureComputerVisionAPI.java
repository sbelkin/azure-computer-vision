package io.sbelkin.azure.computervision.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.sbelkin.azure.computervision.services.storage.configurations.AzureComputerVisionConfiguration;
import io.sbelkin.azure.computervision.consts.AzureComputerVisionConstants;
import io.sbelkin.azure.computervision.exceptions.AzureComputerVisionException;
import io.sbelkin.azure.computervision.models.azure.AzureResponse;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Component
public class AzureComputerVisionAPI {

    private static Logger logger = LoggerFactory.getLogger(AzureComputerVisionAPI.class);

    private final boolean writeToFile = false;
    private final String fileExtension = "_evaluation.json";

    private URI uri;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private AzureComputerVisionConfiguration configuration;

    @PostConstruct
    public void init() throws URISyntaxException {
        //build uri once
        this.uri = uri();
        //tbd PoolingHttpClientConnectionManager
    }

    public AzureResponse getResponse(File file) throws AzureComputerVisionException {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()){
            HttpPost request = new HttpPost(uri);

            // Request headers.
            request.setHeader(AzureComputerVisionConstants.contentTypeHeader, ContentType.APPLICATION_OCTET_STREAM.getMimeType());
            request.setHeader(AzureComputerVisionConstants.subscriptionKeyHeader, configuration.getSubscriptionKey());

            HttpEntity entity = new ByteArrayEntity(IOUtils.toByteArray(file.toURI()));
            request.setEntity(entity);

            // Call the REST API method and get the response entity.
            HttpResponse response = httpClient.execute(request);
            HttpEntity responseEntity = response.getEntity();

            logger.info("Status reponse: {}", response.getStatusLine());
            if (responseEntity != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                AzureResponse azureResponse =  mapper.readValue(responseEntity.getContent(), AzureResponse.class);
                if (writeToFile) {
                    writeToFile(azureResponse, file);
                }
                return azureResponse;
            }
            throw new AzureComputerVisionException("" + response.getStatusLine() + "response: " + EntityUtils.toString(responseEntity));
        } catch (IOException e) {
            throw new AzureComputerVisionException(e);
        }
    }


    // URI built once
    private URI uri() throws URISyntaxException {
        URIBuilder builder = new URIBuilder(configuration.getUrl());

        // Request parameters. All of them are optional.
        builder.setParameter(AzureComputerVisionConstants.visualFeaturesHeader, AzureComputerVisionConstants.visualFeaturesParam);
        builder.setParameter(AzureComputerVisionConstants.languageHeader, AzureComputerVisionConstants.languageParam);

        // Prepare the URI for the REST API method.
        return builder.build();
    }

    // For testing and evaluation purposes
    private void writeToFile(AzureResponse azureResponse, File file) {
        String fileName = FilenameUtils.removeExtension(file.getAbsolutePath()) + fileExtension;
        try {
            mapper.writeValue(new File(fileName), azureResponse);
            logger.debug("Successfully Copied to file: {}", fileName);
        } catch (IOException e) {
            logger.error("Error coping file: {} ", fileName , e);
        }
    }
}
