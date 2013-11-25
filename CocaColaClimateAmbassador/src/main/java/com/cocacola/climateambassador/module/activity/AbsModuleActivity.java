package com.cocacola.climateambassador.module.activity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.core.activity.CaDrawerActivity;
import com.cocacola.climateambassador.core.fragment.CaFragment;
import com.cocacola.climateambassador.core.model.SectionModel;
import com.cocacola.climateambassador.data.DaoMaster;
import com.cocacola.climateambassador.data.Module;
import com.cocacola.climateambassador.data.Section;
import com.cocacola.climateambassador.drawer.adapters.ModuleDrawerListAdapter;
import com.cocacola.climateambassador.drawer.model.ModuleDrawerItem;
import com.cocacola.climateambassador.module.fragment.ModuleFragment;
import com.cocacola.climateambassador.module.internal.activity.InternalModuleThreeActivity;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by realandylawton on 11/24/13.
 */
public class AbsModuleActivity extends CaDrawerActivity implements AdapterView.OnItemClickListener {

    public static final String MODULE_ID_BUNDLE_KEY = "moduleId";
    @Inject protected DaoMaster mDaoMaster;

    private List<ModuleDrawerItem> mDrawerItems;

    @Override protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Set up Menu List
        mMenuAdapter = new ModuleDrawerListAdapter(this, getDrawerItems());
        mDrawerList.setAdapter(mMenuAdapter);
        mDrawerList.setOnItemClickListener(this);


    }

    protected void setContentFragment(CaFragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.module_frag_container, fragment);

        transaction.commit();
    }

    protected Long getModuleIdFromIntent(Intent intent) {

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
                        new ModuleDrawerItem(true, section.getName()));
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

    @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        ModuleDrawerItem item = (ModuleDrawerItem) parent.getItemAtPosition(position);
        if(item.getModule() == null) {
            return;
        }

        mDrawerLayout.closeDrawer(mDrawerList);

        if(item.getModule().getTitle().contains("Key Interventions")) {

            Intent intent = new Intent(this, InternalModuleThreeActivity.class);
            intent.putExtra(MODULE_ID_BUNDLE_KEY, item.getModule().getId());

            startActivity(intent);

        }
        else  {
            ModuleFragment fragment = ModuleFragment.newInstance(item.getModule().getId());
            setContentFragment(fragment);
        }

    }
}
