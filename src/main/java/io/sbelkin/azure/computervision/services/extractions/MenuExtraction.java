package io.sbelkin.azure.computervision.services.extractions;

import io.sbelkin.azure.computervision.exceptions.AzureComputerVisionException;
import io.sbelkin.azure.computervision.models.Beer;
import io.sbelkin.azure.computervision.models.azure.AzureResponse;

import java.util.List;

public interface MenuExtraction {

    List<Beer> getBeerList(AzureResponse response) throws AzureComputerVisionException;

    // need a lookup: https://openbeerdb.com/
    // https://alcohol.stackexchange.com/questions/666/where-can-i-find-open-apis-about-beer
    // https://catalog.data.gov/dataset?tags=beer
}
