package com.cocacola.climateambassador.models;

/**
 * Created by andrewlawton on 8/23/13.
 */
public class Document {

    private String fileName;
    private String label;
    private String fileType;

    public Document(String fileName, String label, String fileType) {
        this.fileName = fileName;
        this.label = label;
        this.fileType = fileType;
    }

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

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
