package io.sbelkin.azure.computervision.services.extractions;

import com.microsoft.azure.cognitiveservices.vision.computervision.models.OcrLine;
import com.microsoft.azure.cognitiveservices.vision.computervision.models.OcrRegion;
import com.microsoft.azure.cognitiveservices.vision.computervision.models.OcrResult;
import com.microsoft.azure.cognitiveservices.vision.computervision.models.OcrWord;
import io.sbelkin.azure.computervision.models.Beer;
import io.sbelkin.azure.computervision.services.beers.BeerLookup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BasicMenuExtraction implements MenuExtraction {

    @Autowired
    private BeerLookup beerLookup;

    private static final String DELIMITER = " ";

    @Override
    public List<Beer> getBeerList(OcrResult result) {
        //        boundingBox
        //  Bounding box of a recognized region, line, or word, depending on the parent object.
        //  The four integers represent the x-coordinate of the left edge,
        //                              the y-coordinate of the top edge,
        //                              width, and
        //                              height
        // of the bounding box, in the coordinate system of the input image, after it has been rotated around its center
        // according to the detected text angle (see textAngle property), with the origin at the top-left corner,
        // and the y-axis pointing down.
        return convert(result);
    }

    private List<Beer> convert(OcrResult result) {
        System.out.println();
        System.out.println("Recognizing printed text from a local image with OCR ...");
        System.out.println("\nLanguage: " + result.language());
        System.out.printf("Text angle: %1.3f\n", result.textAngle());
        System.out.println("Orientation: " + result.orientation());

        boolean firstWord = true;
        // Gets entire region of text block
        for (OcrRegion reg : result.regions()) {
            // Get one line in the text block
            for (OcrLine line : reg.lines()) {
                for (OcrWord word : line.words()) {
                    System.out.print(word.text() + " ");
                }
                System.out.println();
            }
        }
        // TODO: Determine how to use the the OCR Result.
        return new ArrayList<>();
    }
}
