package io.sbelkin.azure.computervision.models.azure;

import java.util.List;

public class AzureRegion {

    private String boundingBox;
    private List<AzureLine> lines;

    public AzureRegion() {
    }

    public String getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(String boundingBox) {
        this.boundingBox = boundingBox;
    }

    public List<AzureLine> getLines() {
        return lines;
    }

    public void setLines(List<AzureLine> lines) {
        this.lines = lines;
    }

    @Override
    public String toString() {
        return "AzureRegion{" +
                "boundingBox='" + boundingBox + '\'' +
                ", lines=" + lines +
                '}';
    }
}
