package com.cocacola.climateambassador.data.json;

import java.util.List;

/**
 * Created by andrewlawton on 8/23/13.
 */
public class SectionJson {

    private String title;
    private String shortTitle;
    private List<String> modules;

    public SectionJson(String title, List<String> modules) {
        this.title = title;
        this.modules = modules;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getModules() {
        return modules;
    }

    public void setModules(List<String> modules) {
        this.modules = modules;
    }
}
