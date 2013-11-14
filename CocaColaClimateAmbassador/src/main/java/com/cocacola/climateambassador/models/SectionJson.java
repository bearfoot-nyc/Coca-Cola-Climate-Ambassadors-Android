package com.cocacola.climateambassador.models;

import java.util.List;

/**
 * Created by andrewlawton on 8/23/13.
 */
public class SectionJson {

    private String title;
    private List<ModuleJson> modules;

    public SectionJson(String title, List<ModuleJson> modules) {
        this.title = title;
        this.modules = modules;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ModuleJson> getModules() {
        return modules;
    }

    public void setModules(List<ModuleJson> modules) {
        this.modules = modules;
    }
}
