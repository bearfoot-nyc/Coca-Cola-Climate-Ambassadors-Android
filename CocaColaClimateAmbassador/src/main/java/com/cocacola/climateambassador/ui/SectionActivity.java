package com.cocacola.climateambassador.ui;

import com.cocacola.climateambassador.adapters.MenuListAdapter;
import com.cocacola.climateambassador.models.NavigationDrawerItem;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by andrewlawton on 9/1/13.
 */
public class SectionActivity extends CaDrawerActivity {
    private static final int DRAWER_POSITION_MODULE_1 = 0;
    private static final int DRAWER_POSITION_MODULE_2 = 1;
    private static final int DRAWER_POSITION_MODULE_3 = 2;
    private static final int DRAWER_POSITION_MODULE_4 = 3;

    @Override
    MenuListAdapter getMenuListAdapter() {
        return new MenuListAdapter(this, getNavigationDrawerItems());
    }

    @Override
    List<NavigationDrawerItem> getNavigationDrawerItems() {
        if(mNavigationDrawerItems == null) {
            mNavigationDrawerItems = new LinkedList<NavigationDrawerItem>();
            mNavigationDrawerItems.add(new NavigationDrawerItem("Module 1", 0));
            mNavigationDrawerItems.add(new NavigationDrawerItem("Module 2", 0));
            mNavigationDrawerItems.add(new NavigationDrawerItem("Module 3", 0));
            mNavigationDrawerItems.add(new NavigationDrawerItem("Module 4", 0));
        }
        return mNavigationDrawerItems;
    }

    @Override
    void onDrawerItemClick(int position) {
        switch (position) {
            case DRAWER_POSITION_MODULE_1:
                break;
            case DRAWER_POSITION_MODULE_2:
                break;
            case DRAWER_POSITION_MODULE_3:
                break;
            case DRAWER_POSITION_MODULE_4:
                break;
        }
    }
}
