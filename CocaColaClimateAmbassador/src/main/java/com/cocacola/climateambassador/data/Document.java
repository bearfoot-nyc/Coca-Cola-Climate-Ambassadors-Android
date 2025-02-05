package com.cocacola.climateambassador.data;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table DOCUMENT.
 */
public class Document {

    private Long id;
    private String fileName;
    private String label;
    private Boolean isFavorite;
    private Long moduleId;
    private Long caseId;

    public Document() {
    }

    public Document(Long id) {
        this.id = id;
    }

    public Document(Long id, String fileName, String label, Boolean isFavorite, Long moduleId, Long caseId) {
        this.id = id;
        this.fileName = fileName;
        this.label = label;
        this.isFavorite = isFavorite;
        this.moduleId = moduleId;
        this.caseId = caseId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(Boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

}
