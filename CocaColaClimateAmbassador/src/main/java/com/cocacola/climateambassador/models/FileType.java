package com.cocacola.climateambassador.models;

/**
 * Created by andrewlawton on 9/6/13.
 */
public enum FileType {

    PDF("application/pdf"), PPT("application/vnd.ms-powerpoint"), VIDEO("video/*");

    public String mimeType;

    FileType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getMimeType() {
        return this.mimeType;
    }

}