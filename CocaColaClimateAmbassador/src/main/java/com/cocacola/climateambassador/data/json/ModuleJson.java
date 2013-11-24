package com.cocacola.climateambassador.data.json;

import java.util.List;

/**
 * Created by andrewlawton on 8/23/13.
 */
public class ModuleJson {

    private String title;
    private String bodyText;
    private List<CaseJson> cases;
    private List<DocumentJson> documents;
    private BulletPointFrameJson bulletPointFrame;

    public ModuleJson(String title, String bodyText, List<CaseJson> cases,
        List<DocumentJson> documents) {
        this.title = title;
        this.bodyText = bodyText;
        this.cases = cases;
        this.documents = documents;
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

    public List<CaseJson> getCases() {
        return cases;
    }

    public void setCases(List<CaseJson> cases) {
        this.cases = cases;
    }


    public BulletPointFrameJson getBulletPointFrame() {
        return bulletPointFrame;
    }

    public void setBulletPointFrame(BulletPointFrameJson bulletPointFrame) {
        this.bulletPointFrame = bulletPointFrame;
    }


    public List<DocumentJson> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentJson> documents) {
        this.documents = documents;
    }



}
