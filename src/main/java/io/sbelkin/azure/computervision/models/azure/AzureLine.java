package io.sbelkin.azure.computervision.models.azure;

import java.util.List;

public class AzureLine {

    private String boundingBox;
    private List<AzureWords> words;

    public AzureLine() {
    }

    public String getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(String boundingBox) {
        this.boundingBox = boundingBox;
    }

    public List<AzureWords> getWords() {
        return words;
    }

    public void setWords(List<AzureWords> words) {
        this.words = words;
    }

    @Override
    public String toString() {
        return "AzureLine{" +
                "boundingBox='" + boundingBox + '\'' +
                '}';
    }
}
