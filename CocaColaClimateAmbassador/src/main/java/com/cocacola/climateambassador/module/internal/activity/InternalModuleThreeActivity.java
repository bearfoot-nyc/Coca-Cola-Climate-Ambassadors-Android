package com.cocacola.climateambassador.module.internal.activity;

import android.os.Bundle;

import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.module.activity.AbsModuleActivity;
import com.cocacola.climateambassador.module.fragment.ModuleFragment;

/**
 * Created by realandylawton on 8/31/13.
 */
public class InternalModuleThreeActivity extends AbsModuleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.module_internal_act_3);

        // Show correct fragment
        Long moduleId = getModuleIdFromIntent(getIntent());
        ModuleFragment fragment = ModuleFragment.newInstance(moduleId);

        setContentFragment(fragment);

    }

}
