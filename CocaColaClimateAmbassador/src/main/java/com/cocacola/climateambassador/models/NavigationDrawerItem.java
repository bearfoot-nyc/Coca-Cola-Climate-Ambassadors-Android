package com.cocacola.climateambassador.models;

import android.content.Context;

/**
 * Created by andrewlawton on 9/1/13.
 */
public class NavigationDrawerItem {

    private int titleId;
    private int iconId;
    private boolean isHeader;
    private Class<?> activityClz;

    public NavigationDrawerItem(int titleId, int iconId, boolean header, Class<?> activityClz) {
        this.titleId = titleId;
        this.iconId = iconId;
        isHeader = header;
        this.activityClz = activityClz;
    }

    public int getIconId() {
        return iconId;
    }

    public int getTitleId() {
        return titleId;
    }

    public String getTitle(Context context) {
        return context.getResources().getString(titleId);
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
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
