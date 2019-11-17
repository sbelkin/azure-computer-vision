package io.sbelkin.azure.computervision.models;

import java.util.List;

public class Region {

    private String boundingBox;
    private List<String> sentences;

    public Region() {
    }

    public Region(String boundingBox, List<String> sentences) {
        this.boundingBox = boundingBox;
        this.sentences = sentences;
    }

    public String getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(String boundingBox) {
        this.boundingBox = boundingBox;
    }

    public List<String> getSentences() {
        return sentences;
    }

    public void setSentences(List<String> sentences) {
        this.sentences = sentences;
    }

    @Override
    public String toString() {
        return "Region{" +
                "boundingBox='" + boundingBox + '\'' +
                ", sentences=" + sentences +
                '}';
    }
}
