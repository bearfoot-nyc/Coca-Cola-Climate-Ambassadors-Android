package com.cocacola.climateambassador.ui.activities;

import android.widget.Toast;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.adapters.MenuListAdapter;
import com.cocacola.climateambassador.data.DaoMaster;
import com.cocacola.climateambassador.data.Module;
import com.cocacola.climateambassador.data.Section;
import com.cocacola.climateambassador.data.SectionDao;
import com.cocacola.climateambassador.json.NavigationDrawerItem;

import com.cocacola.climateambassador.models.SectionModel;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by andrewlawton on 9/1/13.
 */
public class SectionActivity extends CaDrawerActivity {

    @Inject DaoMaster mDaoMaster;

    @Override
    MenuListAdapter getMenuListAdapter() {
        return new MenuListAdapter(this, getNavigationDrawerItems());
    }

    @Override
    List<NavigationDrawerItem> getNavigationDrawerItems(){

        if(mNavigationDrawerItems == null) {

            try {
                List<Section> sectionList = SectionModel.getAllSections(mDaoMaster);
                mNavigationDrawerItems = new LinkedList<NavigationDrawerItem>();

                for(Section section : sectionList) {
                    mNavigationDrawerItems.add(new NavigationDrawerItem(section.getName(), 0, true, InternalTrainingActivity.class));
                    for(Module module : section.getModules()) {
                        mNavigationDrawerItems.add(new NavigationDrawerItem(module.getTitle(), 0, false, InternalTrainingActivity.class));
                    }
                }

            }
            catch (Exception e) {
                Toast.makeText(this, "There was an error fetching sections", Toast.LENGTH_SHORT).show();;
            }

        }
        return mNavigationDrawerItems;
    }

}
