package com.cocacola.climateambassador.models;

import java.util.List;

/**
 * Created by andrewlawton on 8/23/13.
 */

public class CaseJson {

    public static enum CaseDocumentTypes {
        COURSE_MATERIALS, CASE_STUDIES
    }

    private String title;
    private String bodyText;
    private BulletPointFrameJson bulletPointFrame;
    private List<TextFrameJson> textFrames;
    private List<DocumentJson> courseMaterials;
    private List<DocumentJson> caseStudies;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BulletPointFrameJson getBulletPointFrame() {
        return bulletPointFrame;
    }

    public void setBulletPointFrame(BulletPointFrameJson bulletPointFrame) {
        this.bulletPointFrame = bulletPointFrame;
    }

    public List<TextFrameJson> getTextFrames() {
        return textFrames;
    }

    public void setTextFrames(List<TextFrameJson> textFrames) {
        this.textFrames = textFrames;
    }

    public String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }

    public List<DocumentJson> getCourseMaterials() {
        return courseMaterials;
    }

    public void setCourseMaterials(List<DocumentJson> courseMaterials) {
        this.courseMaterials = courseMaterials;
    }

    public List<DocumentJson> getCaseStudies() {
        return caseStudies;
    }

    public void setCaseStudies(List<DocumentJson> caseStudies) {
        this.caseStudies = caseStudies;
    }
}
