package com.cocacola.climateambassador.models;

/**
 * Created by andrewlawton on 9/6/13.
 */
public enum FileType {

    PDF("application/pdf", "pdf", "docs"),
    PPT("application/vnd.ms-powerpoint", "pptx", "docs"),
    VIDEO("video/mp4", "mp4", "docs");

    private String extension;
    private String mimeType;
    private String directory;

    FileType(String mimeType, String extension, String directory) {
        this.mimeType = mimeType;
        this.extension = extension;
        this.directory = directory;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public String getExtension() {
        return this.extension;
    }

    public String getDirectory() {
        return directory;
    }

    public static FileType getTypeForFilename(String filename) {

        String extension = getExtensionFromFilename(filename);
        FileType type = getTypeForExtension(extension);

        return type;

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

    public static String getExtensionFromFilename(String fileName) {

        String extension = "";

        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i + 1);
        }

        return extension;

    }

}