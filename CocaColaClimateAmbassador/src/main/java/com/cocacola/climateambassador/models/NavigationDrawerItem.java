package com.cocacola.climateambassador.models;

/**
 * Created by andrewlawton on 9/1/13.
 */
public class NavigationDrawerItem {

    private String title;
    private int iconId;

    public NavigationDrawerItem(String title, int iconId) {
        this.title = title;
        this.iconId = iconId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }
}
