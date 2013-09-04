package com.cocacola.climateambassador.models;

/**
 * Created by andrewlawton on 9/1/13.
 */
public class NavigationDrawerItem {

    private String title;
    private int iconId;
    private boolean isHeader;
    private Class<?> activityClz;

    public NavigationDrawerItem(String title, int iconId, boolean header, Class<?> activityClz) {
        this.title = title;
        this.iconId = iconId;
        isHeader = header;
        this.activityClz = activityClz;
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

    public boolean isHeader() {
        return isHeader;
    }

    public void setHeader(boolean header) {
        isHeader = header;
    }

    public Class<?> getActivityClz() {
        return activityClz;
    }

    public void setActivityClz(Class<?> activityClz) {
        this.activityClz = activityClz;
    }
}
