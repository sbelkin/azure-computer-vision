package io.sbelkin.azure.computervision.models.azure;

import java.util.List;

public class AzureResponse {

    private String orientation;
    private List<AzureRegion> regions;
    private Double textAngle;
    private String language;

    public AzureResponse() {
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public List<AzureRegion> getRegions() {
        return regions;
    }

    public void setRegions(List<AzureRegion> regions) {
        this.regions = regions;
    }

    public Double getTextAngle() {
        return textAngle;
    }

    public void setTextAngle(Double textAngle) {
        this.textAngle = textAngle;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "AzureResponse{" +
                "orientation='" + orientation + '\'' +
                ", regions=" + regions +
                ", textAngle=" + textAngle +
                ", language='" + language + '\'' +
                '}';
    }
}
