package com.cocacola.climateambassador.models;

/**
 * Created by andrewlawton on 8/23/13.
 */
public class Document {

    public static enum FileType {
        PDF, PPT, DOC
    }

    public Document(String fileName, String label) {
        this.fileName = fileName;
        this.label = label;
    }

    private String fileName;
    private String label;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
