package com.cocacola.climateambassador.models;

import java.util.List;

/**
 * Created by andrewlawton on 8/23/13.
 */

public class Case {

    public static enum CaseDocumentTypes {
        COURSE_MATERIALS, CASE_STUDIES
    }

    private String title;
    private String bodyText;
    private BulletPointFrame bulletPointFrame;
    private List<TextFrame> textFrames;
    private List<Document> courseMaterials;
    private List<Document> caseStudies;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BulletPointFrame getBulletPointFrame() {
        return bulletPointFrame;
    }

    public void setBulletPointFrame(BulletPointFrame bulletPointFrame) {
        this.bulletPointFrame = bulletPointFrame;
    }

    public List<TextFrame> getTextFrames() {
        return textFrames;
    }

    public void setTextFrames(List<TextFrame> textFrames) {
        this.textFrames = textFrames;
    }

    public String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }

    public List<Document> getCourseMaterials() {
        return courseMaterials;
    }

    public void setCourseMaterials(List<Document> courseMaterials) {
        this.courseMaterials = courseMaterials;
    }

    public List<Document> getCaseStudies() {
        return caseStudies;
    }

    public void setCaseStudies(List<Document> caseStudies) {
        this.caseStudies = caseStudies;
    }
}
