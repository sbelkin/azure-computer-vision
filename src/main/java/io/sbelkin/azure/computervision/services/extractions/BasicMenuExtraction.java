package io.sbelkin.azure.computervision.services.extractions;

import io.sbelkin.azure.computervision.api.AzureComputerVisionAPI;
import io.sbelkin.azure.computervision.exceptions.AzureComputerVisionException;
import io.sbelkin.azure.computervision.models.Beer;
import io.sbelkin.azure.computervision.models.Region;
import io.sbelkin.azure.computervision.models.azure.AzureLine;
import io.sbelkin.azure.computervision.models.azure.AzureResponse;
import io.sbelkin.azure.computervision.models.azure.AzureWords;
import io.sbelkin.azure.computervision.services.beers.BeerLookup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BasicMenuExtraction implements MenuExtraction {

    @Autowired
    private BeerLookup beerLookup;

    private static final String DELIMITER = " ";

    @Override
    public List<Beer> getBeerList(AzureResponse response) throws AzureComputerVisionException {
        //        boundingBox
        //  Bounding box of a recognized region, line, or word, depending on the parent object.
        //  The four integers represent the x-coordinate of the left edge,
        //                              the y-coordinate of the top edge,
        //                              width, and
        //                              height
        // of the bounding box, in the coordinate system of the input image, after it has been rotated around its center
        // according to the detected text angle (see textAngle property), with the origin at the top-left corner,
        // and the y-axis pointing down.
        List<Region> regions = response.getRegions().stream()
                .map(azureRegion -> new Region(azureRegion.getBoundingBox(), convert(azureRegion.getLines())))
                .collect(Collectors.toList());
        return new ArrayList<>();
    }

    private List<String> convert(List<AzureLine> lines) {
        return lines.stream()
                .map(line -> line.getWords().stream()
                        .map(AzureWords::getText)
                        .collect(Collectors.joining(DELIMITER)))
                .collect(Collectors.toList());
    }

    static class AzureRegionBoxComparator implements Comparator<AzureWords> {
        public int compare(AzureWords c1, AzureWords c2) {
            Integer a1 = Integer.valueOf(c1.getBoundingBox().split(",")[0]);
            Integer a2 = Integer.valueOf(c2.getBoundingBox().split(",")[0]);
            return a1.compareTo(a2);
        }
    }
}
