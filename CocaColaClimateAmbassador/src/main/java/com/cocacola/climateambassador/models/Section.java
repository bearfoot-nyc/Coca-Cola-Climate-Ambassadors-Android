package com.cocacola.climateambassador.models;

import java.util.List;

/**
 * Created by andrewlawton on 8/23/13.
 */
public class Section {

    private String title;
    private List<Module> modules;

    public Section(String title, List<Module> modules) {
        this.title = title;
        this.modules = modules;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }
}
