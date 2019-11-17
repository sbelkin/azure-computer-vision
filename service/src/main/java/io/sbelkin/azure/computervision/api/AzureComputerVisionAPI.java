package io.sbelkin.azure.computervision.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.azure.cognitiveservices.vision.computervision.ComputerVisionClient;
import com.microsoft.azure.cognitiveservices.vision.computervision.ComputerVisionManager;
import com.microsoft.azure.cognitiveservices.vision.computervision.models.OcrLanguages;
import com.microsoft.azure.cognitiveservices.vision.computervision.models.OcrResult;
import io.sbelkin.azure.computervision.services.storage.configurations.AzureComputerVisionConfiguration;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;

@Component
public class AzureComputerVisionAPI {

    private static Logger logger = LoggerFactory.getLogger(AzureComputerVisionAPI.class);

    private final boolean writeToFile = false;
    private final String fileExtension = "_evaluation.json";

    private ComputerVisionClient client;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private AzureComputerVisionConfiguration configuration;

    @PostConstruct
    public void init() throws URISyntaxException {
        //Computer vision client
        this.client = ComputerVisionManager
                .authenticate(configuration.getSubscriptionKey())
                .withEndpoint(configuration.getEndpoint());
        //tbd PoolingHttpClientConnectionManager
    }

    public OcrResult getResponse(File file) throws IOException {
        byte[] localImageBytes = Files.readAllBytes(file.toPath());
        OcrResult ocrResultLocal = client.computerVision().recognizePrintedTextInStream()
                .withDetectOrientation(true).withImage(localImageBytes).withLanguage(OcrLanguages.EN).execute();
        return ocrResultLocal;
    }


    // For testing and evaluation purposes
    private void writeToFile(OcrResult azureResponse, File file) {
        String fileName = FilenameUtils.removeExtension(file.getAbsolutePath()) + fileExtension;
        try {
            mapper.writeValue(new File(fileName), azureResponse);
            logger.debug("Successfully Copied to file: {}", fileName);
        } catch (IOException e) {
            logger.error("Error coping file: {} ", fileName, e);
        }
    }
}
