package com.cocacola.climateambassador.data.json;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by andrewlawton on 8/23/13.
 */
public class ModuleJson {

    private String title;
    private String shortTitle;
    private String bodyText;
    @SerializedName("caseStudies") private List<String> cases;
    private List<DocumentJson> documents;
    private BulletPointFrameJson bulletPointFrame;


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

    public List<String> getCases() {
        return cases;
    }

    public void setCases(List<String> cases) {
        this.cases = cases;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }
}
