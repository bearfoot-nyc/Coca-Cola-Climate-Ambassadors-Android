package com.cocacola.climateambassador.models;

/**
 * Created by andrewlawton on 9/6/13.
 */
public enum FileType {

    PDF("application/pdf", "pdf"),
    PPT("application/vnd.ms-powerpoint", "pptx"),
    VIDEO("video/*", "mp4");

    public String extension;
    public String mimeType;

    FileType(String mimeType, String extension) {
        this.mimeType = mimeType;
        this.extension = extension;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public String getExtension() {
        return this.extension;
    }

    public static FileType getTypeForExtension(String extension) {

        FileType fileType = null;

        if(extension.equals(FileType.PDF.getExtension())) {
            fileType = FileType.PDF;
        }
        else if(extension.equals(FileType.PPT.getExtension())) {
            fileType = FileType.PPT;
        }
        else  if(extension.equals(FileType.VIDEO.getExtension())) {
            fileType = FileType.VIDEO;
        }

        return fileType;

    }

}