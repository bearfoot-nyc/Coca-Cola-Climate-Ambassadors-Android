package com.cocacola.climateambassador.models;

import java.util.List;

/**
 * Created by andrewlawton on 8/23/13.
 */
public class Module {

    private String bodyText;
    private String videoUri;
    private Document document;
    private List<Case> cases;

    public Module(String bodyText, String videoUri, Document document, List<Case> cases) {
        this.bodyText = bodyText;
        this.videoUri = videoUri;
        this.document = document;
        this.cases = cases;
    }

    public String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }

    public String getVideoUri() {
        return videoUri;
    }

    public void setVideoUri(String videoUri) {
        this.videoUri = videoUri;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public List<Case> getCases() {
        return cases;
    }

    public void setCases(List<Case> cases) {
        this.cases = cases;
    }
}
