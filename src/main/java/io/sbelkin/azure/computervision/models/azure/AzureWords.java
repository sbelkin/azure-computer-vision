package io.sbelkin.azure.computervision.models.azure;

public class AzureWords {

    private String boundingBox;
    private String text;

    public AzureWords() {
    }

    public String getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(String boundingBox) {
        this.boundingBox = boundingBox;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "AzureWords{" +
                "boundingBox='" + boundingBox + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
