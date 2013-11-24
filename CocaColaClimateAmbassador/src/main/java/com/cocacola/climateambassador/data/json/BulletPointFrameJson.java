package com.cocacola.climateambassador.data.json;

import java.util.List;

/**
 * Created by realandylawton on 8/31/13.
 */
public class BulletPointFrameJson {

    private String title;
    private String subtitle;
    private List<String> bulletPoints;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public List<String> getBulletPoints() {
        return bulletPoints;
    }

    public void setBulletPoints(List<String> bulletPoints) {
        this.bulletPoints = bulletPoints;
    }

}
