package com.cocacola.climateambassador.module.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.widget.AdapterView;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.core.CaConstants;
import com.cocacola.climateambassador.core.activity.CaDrawerSearchableActivity;
import com.cocacola.climateambassador.core.fragment.CaFragment;
import com.cocacola.climateambassador.core.model.SectionModel;
import com.cocacola.climateambassador.core.util.Toaster;
import com.cocacola.climateambassador.data.DaoMaster;
import com.cocacola.climateambassador.data.Module;
import com.cocacola.climateambassador.data.Navigable;
import com.cocacola.climateambassador.data.Section;
import com.cocacola.climateambassador.module.fragment.ModuleFragment;
import com.cocacola.climateambassador.module.internal.activity.ModuleCasesActivity;
import com.cocacola.climateambassador.module.suppliers.fragment.ForSupplierOverviewFragment;
import com.cocacola.climateambassador.module.suppliers.fragment.ForSuppliersVisionFragment;
import javax.inject.Inject;

/**
 * Created by realandylawton on 11/24/13.
 */
public abstract class AbsModuleActivity extends CaDrawerSearchableActivity {

    public static final int MODULE_TYPE_INTERVENTION = 1;
    public static final int MODULE_TYPE_SUSTAINABLE = 2;
    public static final int MODULE_TYPE_VISION = 3;
    public static final String EXTRA_MODULE_ID = "moduleId";
    public static final String EXTRA_MODULE_TYPE = "moduleType";

    @Inject protected DaoMaster mDaoMaster;

    @Override public void onModuleItemClick(Navigable item) {

        if(item.getShortTitle().contains("Key Interventions")) {

            Intent intent = new Intent(this, ModuleCasesActivity.class);
            intent.putExtra(EXTRA_MODULE_ID, item.getId());

            startActivity(intent);


        }
        else if(item.getTitle().contains("Sustainable")) {

            // FIXME Load from Module instead of Json
            ForSupplierOverviewFragment fragment = ForSupplierOverviewFragment.newInstance();
            setContentFragment(fragment);

        }
        else if(item.getTitle().contains("2020 Vision")) {

            ForSuppliersVisionFragment fragment = ForSuppliersVisionFragment.newInstance(item.getId());
            setContentFragment(fragment);

        }
        else  {
            ModuleFragment fragment = ModuleFragment.newInstance(item.getId());
            setContentFragment(fragment);
        }

    }

    @Override public void onSectionItemClick(Navigable item) {

        Section section = SectionModel.getSection(mDaoMaster, item.getId());

        if(section != null && section.getModules() != null && section.getModules().size() > 0) {

            CaFragment fragment;

            if(section.getId() == CaConstants.SECTION_ID_INTERNAL) {

                fragment = ForSupplierOverviewFragment.newInstance();

            }
            else {
                Module module = section.getModules().get(0);

                fragment = ModuleFragment.newInstance(module.getId());
            }

            setContentFragment(fragment);

        }
        else {
            Toaster.toast(this, "Failed to load Section: " + item.getId());
        }

    }

    protected void setContentFragment(CaFragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.module_frag_container, fragment);

        transaction.commit();
    }

    protected Long getModuleIdFromIntent(Intent intent) {

        if(!intent.hasExtra(EXTRA_MODULE_ID)) {
            return null;
        }

        Long id = intent.getLongExtra(EXTRA_MODULE_ID, 0);

        return id;

    }

    protected Integer getModuleTypeFromIntent(Intent intent) {

        if(!intent.hasExtra(EXTRA_MODULE_TYPE)) {
            return 0;
        }

        return intent.getIntExtra(EXTRA_MODULE_TYPE, 0);

    }

}
