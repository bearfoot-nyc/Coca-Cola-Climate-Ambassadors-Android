package com.cocacola.climateambassador.ui.activities;

import android.os.Bundle;

import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.ui.activities.CaActivity;

/**
 * Created by realandylawton on 8/31/13.
 */
public class ModuleThreeActivity extends SectionActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_three);
        setupNavigationDrawer();
    }

}
