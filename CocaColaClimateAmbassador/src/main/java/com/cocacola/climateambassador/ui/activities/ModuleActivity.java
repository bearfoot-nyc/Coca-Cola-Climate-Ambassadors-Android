package com.cocacola.climateambassador.ui.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.data.DaoMaster;
import com.cocacola.climateambassador.data.Module;
import com.cocacola.climateambassador.data.Section;
import com.cocacola.climateambassador.drawer.adapters.ModuleDrawerListAdapter;

import com.cocacola.climateambassador.drawer.model.ModuleDrawerItem;
import com.cocacola.climateambassador.models.SectionModel;
import com.cocacola.climateambassador.ui.fragments.ModuleOneBodyFragment;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by andrewlawton on 9/1/13.
 */
public class ModuleActivity extends CaDrawerActivity {

    public static final String MODULE_ID_BUNDLE_KEY = "moduleId";

    @Inject protected DaoMaster mDaoMaster;

    private List<ModuleDrawerItem> mDrawerItems;

    @Override protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.module_act);

        super.onCreate(savedInstanceState);

        // Set up Menu List
        mMenuAdapter = new ModuleDrawerListAdapter(this, getDrawerItems());

        // Show the proper fragment
        Long moduleId = getModuleIdFromIntent(getIntent());
        ModuleOneBodyFragment fragment = ModuleOneBodyFragment.newInstance(moduleId);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.module_frag_container, fragment);

        transaction.commit();

    }

    private Long getModuleIdFromIntent(Intent intent) {

        if(!intent.hasExtra(MODULE_ID_BUNDLE_KEY)) {
            return null;
        }

        Long id = intent.getLongExtra(MODULE_ID_BUNDLE_KEY, 0);

        return id;

    }

    public List<ModuleDrawerItem> getDrawerItems(){

        if(mDrawerItems == null) {

            try {
                List<Section> sectionList = SectionModel.getAllSections(mDaoMaster);
                mDrawerItems = new LinkedList<ModuleDrawerItem>();

                for(Section section : sectionList) {
                    mDrawerItems.add(
                        new ModuleDrawerItem(null, true));
                    for(Module module : section.getModules()) {
                        mDrawerItems.add(new ModuleDrawerItem(module, false));
                    }
                }

            }
            catch (Exception e) {
                Toast.makeText(this, "There was an error fetching sections", Toast.LENGTH_SHORT).show();;
            }

        }
        return mDrawerItems;
    }

}
