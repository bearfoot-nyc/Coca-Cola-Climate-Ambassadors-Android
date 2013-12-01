package com.cocacola.climateambassador.module.suppliers.activity;

import android.os.Bundle;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.module.activity.AbsModuleActivity;
import com.cocacola.climateambassador.module.activity.SectionModuleActivity;
import com.cocacola.climateambassador.module.suppliers.fragment.ForSupplierOverviewFragment;

/**
 * Created by realandylawton on 11/27/13.
 */
public class ForSuppliersOverviewActivity extends AbsModuleActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.module_activity);

        ForSupplierOverviewFragment fragment = ForSupplierOverviewFragment.newInstance();
        setContentFragment(fragment);

    }
}
