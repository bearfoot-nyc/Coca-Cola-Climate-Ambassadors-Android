package com.cocacola.climateambassador.module.activity;

import android.os.Bundle;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.core.fragment.CaFragment;
import com.cocacola.climateambassador.module.fragment.ModuleFragment;
import com.cocacola.climateambassador.module.internal.fragment.CaseStudiesListFragment;
import com.cocacola.climateambassador.module.internal.fragment.ModuleCasesFragment;
import com.cocacola.climateambassador.module.suppliers.fragment.ForSupplierOverviewFragment;
import com.cocacola.climateambassador.module.suppliers.fragment.ForSuppliersVisionFragment;

/**
 * Created by andrewlawton on 9/1/13.
 */
public class ModuleActivity extends AbsModuleActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.module_activity);

        // Show correct fragment
        Long moduleId = getModuleIdFromIntent(getIntent());
        Integer moduleType = getModuleTypeFromIntent(getIntent());

        CaFragment fragment;

        switch (moduleType) {
            case MODULE_TYPE_SUSTAINABLE:
                fragment = ForSupplierOverviewFragment.newInstance();
                break;
            case MODULE_TYPE_VISION:
                fragment = ForSuppliersVisionFragment.newInstance(moduleId);
                break;
            case MODULE_TYPE_CASES:
                fragment = ModuleCasesFragment.newInstance(moduleId);
                break;
            default:
                fragment = ModuleFragment.newInstance(moduleId);
                break;
        }

        setContentFragment(fragment);

    }

}
