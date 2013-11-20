package com.cocacola.climateambassador.json;

import android.content.Context;

/**
 * Created by andrewlawton on 9/1/13.
 */
public class NavigationDrawerItem {

    private String title;
    private int iconId;
    private boolean isHeader;
    private Class<?> activityClz;

    public NavigationDrawerItem(String titleId, int iconId, boolean header, Class<?> activityClz) {
        this.title = titleId;
        this.iconId = iconId;
        isHeader = header;
        this.activityClz = activityClz;
    }

    public int getIconId() {
        return iconId;
    }

    public String getTitle() {
        return title;
    }

    public String getTitle(Context context) {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
