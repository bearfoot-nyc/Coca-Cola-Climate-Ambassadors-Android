package com.cocacola.climateambassador.ui.activities;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.data.DaoMaster;
import com.cocacola.climateambassador.data.Module;
import com.cocacola.climateambassador.data.Section;
import com.cocacola.climateambassador.drawer.adapters.ModuleDrawerListAdapter;
import com.cocacola.climateambassador.drawer.model.ModuleDrawerItem;
import com.cocacola.climateambassador.models.SectionModel;
import com.cocacola.climateambassador.ui.fragments.SimpleModuleFragment;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by andrewlawton on 9/1/13.
 */
public class ModuleActivity extends CaDrawerActivity implements AdapterView.OnItemClickListener {

    public static final String MODULE_ID_BUNDLE_KEY = "moduleId";

    @Inject protected DaoMaster mDaoMaster;

    private List<ModuleDrawerItem> mDrawerItems;

    @Override protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.module_act);

        super.onCreate(savedInstanceState);

        // Set up Menu List
        mMenuAdapter = new ModuleDrawerListAdapter(this, getDrawerItems());
        mDrawerList.setAdapter(mMenuAdapter);
        mDrawerList.setOnItemClickListener(this);

        // Show the proper fragment
        Long moduleId = getModuleIdFromIntent(getIntent());
        SimpleModuleFragment fragment = SimpleModuleFragment.newInstance(moduleId);

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

        Intent intent = new Intent(this, ModuleActivity.class);
        intent.putExtra(ModuleActivity.MODULE_ID_BUNDLE_KEY, item.getModule().getId());

        startActivity(intent);


    }
}
