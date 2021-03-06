package io.sbelkin.azure.computervision.services;

import com.microsoft.azure.cognitiveservices.vision.computervision.models.OcrResult;
import io.sbelkin.azure.computervision.api.AzureComputerVisionAPI;
import io.sbelkin.azure.computervision.exceptions.AzureComputerVisionException;
import io.sbelkin.azure.computervision.models.Beer;
import io.sbelkin.azure.computervision.services.extractions.MenuExtraction;
import io.sbelkin.azure.computervision.services.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class BeerListExtraction {

    @Autowired
    private StorageService storageService;

    @Autowired
    private AzureComputerVisionAPI azureComputerVisionAPI;

    @Autowired
    private MenuExtraction menuExtraction;

    public List<Beer> convert(MultipartFile file) throws IOException, AzureComputerVisionException {
        // store file
        File uploaded = storageService.store(file);
        // get ocr response
        OcrResult ocrResult = azureComputerVisionAPI.getResponse(uploaded);
        // extract beer list
        return menuExtraction.getBeerList(ocrResult);
    }
}
