package com.cocacola.climateambassador.models;

import java.util.List;

/**
 * Created by andrewlawton on 8/23/13.
 */
public class Module {

    private String title;
    private String bodyText;
    private List<Case> cases;
    private List<Document> courseMaterials;
    private BulletPointFrame bulletPointFrame;

    public Module(String title, String bodyText, List<Case> cases, List<Document> courseMaterials) {
        this.title = title;
        this.bodyText = bodyText;
        this.cases = cases;
        this.courseMaterials = courseMaterials;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }

    public List<Case> getCases() {
        return cases;
    }

    public void setCases(List<Case> cases) {
        this.cases = cases;
    }


    public BulletPointFrame getBulletPointFrame() {
        return bulletPointFrame;
    }

    public void setBulletPointFrame(BulletPointFrame bulletPointFrame) {
        this.bulletPointFrame = bulletPointFrame;
    }


    public List<Document> getCourseMaterials() {
        return courseMaterials;
    }

    public void setCourseMaterials(List<Document> courseMaterials) {
        this.courseMaterials = courseMaterials;
    }



}
