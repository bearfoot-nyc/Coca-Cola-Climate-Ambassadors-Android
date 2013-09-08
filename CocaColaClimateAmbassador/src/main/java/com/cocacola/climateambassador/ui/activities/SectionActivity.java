package com.cocacola.climateambassador.ui.activities;

import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.adapters.MenuListAdapter;
import com.cocacola.climateambassador.models.NavigationDrawerItem;
import com.cocacola.climateambassador.ui.fragments.ModuleFourBodyFragment;
import com.cocacola.climateambassador.ui.fragments.ModuleOneBodyFragment;
import com.cocacola.climateambassador.ui.fragments.ModuleTwoBodyFragment;

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
            mNavigationDrawerItems.add(new NavigationDrawerItem(R.string.nav_item_internal_training, 0, true, null));
            mNavigationDrawerItems.add(new NavigationDrawerItem(R.string.nav_item_internal_training_overview, 0, false, InternalTrainingActivity.class));
            mNavigationDrawerItems.add(new NavigationDrawerItem(R.string.nav_item_internal_training_climate_ambassador, 0, false, ClimateAmbassadorActivity.class));
            mNavigationDrawerItems.add(new NavigationDrawerItem(R.string.nav_item_internal_training_module_1, 0, false, ModuleOneActivity.class));
            mNavigationDrawerItems.add(new NavigationDrawerItem(R.string.nav_item_internal_training_module_2, 0, false, ModuleTwoActivity.class));
            mNavigationDrawerItems.add(new NavigationDrawerItem(R.string.nav_item_internal_training_module_3, 0, false, ModuleThreeActivity.class));
            mNavigationDrawerItems.add(new NavigationDrawerItem(R.string.nav_item_internal_training_module_4, 0, false, ModuleFourActivity.class));
            mNavigationDrawerItems.add(new NavigationDrawerItem(R.string.nav_item_for_suppliers, 0, true, null));
            mNavigationDrawerItems.add(new NavigationDrawerItem(R.string.nav_item_for_suppliers_overview, 0, false, ForSuppliersActivity.class));
            mNavigationDrawerItems.add(new NavigationDrawerItem(R.string.nav_item_for_suppliers_2020_vision, 0, false, VisionActivity.class));
        }
        return mNavigationDrawerItems;
    }

}
