package com.cocacola.climateambassador.module.activity;

import android.os.Bundle;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.module.fragment.ModuleFragment;

/**
 * Created by andrewlawton on 9/1/13.
 */
public class ModuleActivity extends AbsModuleActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.module_act);

        super.onCreate(savedInstanceState);

        // Show correct fragment
        Long moduleId = getModuleIdFromIntent(getIntent());
        ModuleFragment fragment = ModuleFragment.newInstance(moduleId);

        setContentFragment(fragment);

    }

}
