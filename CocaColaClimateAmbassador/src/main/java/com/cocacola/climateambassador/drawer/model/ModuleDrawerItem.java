package com.cocacola.climateambassador.drawer.model;

import com.cocacola.climateambassador.data.Module;

/**
 * Created by realandylawton on 11/23/13.
 */
public class ModuleDrawerItem extends DrawerItem {

    private Module mModule;
    private boolean mIsHeader;

    public ModuleDrawerItem(Module module, boolean isHeader) {
        mModule = module;
        mIsHeader = isHeader;
    }

    public Module getModule() {
        return mModule;
    }

    public void setModule(Module module) {
        mModule = module;
    }

    public boolean isHeader() {
        return mIsHeader;
    }

    public void setHeader(boolean isHeader) {
        mIsHeader = isHeader;
    }

    @Override public String getTitle() {
        return mModule.getTitle();
    }
}
