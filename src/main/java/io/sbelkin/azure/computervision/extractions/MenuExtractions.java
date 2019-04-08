package io.sbelkin.azure.computervision.extractions;

import io.sbelkin.azure.computervision.api.AzureComputerVisionAPI;
import io.sbelkin.azure.computervision.exceptions.AzureComputerVisionException;
import io.sbelkin.azure.computervision.models.Region;
import io.sbelkin.azure.computervision.models.azure.AzureLine;
import io.sbelkin.azure.computervision.models.azure.AzureResponse;
import io.sbelkin.azure.computervision.models.azure.AzureWords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO: work
 */
@Component
public class MenuExtractions {

    @Autowired
    private AzureComputerVisionAPI computerVisionAPI;

    private static final String DELIMITER = " ";

    public List<Region> getBeerList(File file) throws AzureComputerVisionException {
        AzureResponse response = computerVisionAPI.getResponse(file);
        return convert(response);
    }

    private List<Region> convert(AzureResponse response) {
        //        boundingBox
        //  Bounding box of a recognized region, line, or word, depending on the parent object.
        //  The four integers represent the x-coordinate of the left edge,
        //                              the y-coordinate of the top edge,
        //                              width, and
        //                              height
        // of the bounding box, in the coordinate system of the input image, after it has been rotated around its center
        // according to the detected text angle (see textAngle property), with the origin at the top-left corner,
        // and the y-axis pointing down.
        return response.getRegions().stream()
                .map(azureRegion -> new Region(azureRegion.getBoundingBox(), convert(azureRegion.getLines())))
                .collect(Collectors.toList());
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

    // need a lookup: https://openbeerdb.com/
    // https://alcohol.stackexchange.com/questions/666/where-can-i-find-open-apis-about-beer
    // https://catalog.data.gov/dataset?tags=beer
}
