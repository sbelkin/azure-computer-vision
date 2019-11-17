package io.sbelkin.azure.computervision.services.extractions;

import com.microsoft.azure.cognitiveservices.vision.computervision.models.OcrResult;
import io.sbelkin.azure.computervision.exceptions.AzureComputerVisionException;
import io.sbelkin.azure.computervision.models.Beer;

import java.util.List;

public interface MenuExtraction {

    List<Beer> getBeerList(OcrResult result) throws AzureComputerVisionException;

    // need a lookup: https://openbeerdb.com/
    // https://alcohol.stackexchange.com/questions/666/where-can-i-find-open-apis-about-beer
    // https://catalog.data.gov/dataset?tags=beer
}
