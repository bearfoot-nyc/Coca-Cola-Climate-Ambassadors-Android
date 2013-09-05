package com.cocacola.climateambassador.ui.activities;

import com.cocacola.climateambassador.adapters.MenuListAdapter;
import com.cocacola.climateambassador.models.NavigationDrawerItem;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by andrewlawton on 9/1/13.
 */
public class SectionActivity extends CaDrawerActivity {

    @Override
    MenuListAdapter getMenuListAdapter() {
        return new MenuListAdapter(this, getNavigationDrawerItems());
    }

    @Override
    List<NavigationDrawerItem> getNavigationDrawerItems() {
        if(mNavigationDrawerItems == null) {
            mNavigationDrawerItems = new LinkedList<NavigationDrawerItem>();
            mNavigationDrawerItems.add(new NavigationDrawerItem("Internal Training Materials", 0, true, null));
            mNavigationDrawerItems.add(new NavigationDrawerItem("Overview", 0, false, InternalTrainingActivity.class));
            mNavigationDrawerItems.add(new NavigationDrawerItem("Module 1", 0, false, SectionActivity.class));
            mNavigationDrawerItems.add(new NavigationDrawerItem("Module 2", 0, false, SectionActivity.class));
            mNavigationDrawerItems.add(new NavigationDrawerItem("Module 3", 0, false, SectionActivity.class));
            mNavigationDrawerItems.add(new NavigationDrawerItem("Module 4", 0, false, SectionActivity.class));
            mNavigationDrawerItems.add(new NavigationDrawerItem("For Suppliers", 0, true, null));
            mNavigationDrawerItems.add(new NavigationDrawerItem("Overview", 0, false, ForSuppliersActivity.class));
        }
        return mNavigationDrawerItems;
    }

}
