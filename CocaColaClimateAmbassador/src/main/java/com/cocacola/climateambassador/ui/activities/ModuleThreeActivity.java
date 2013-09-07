package com.cocacola.climateambassador.ui.activities;

import android.os.Bundle;

import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.ui.activities.CaActivity;

/**
 * Created by realandylawton on 8/31/13.
 */
public class ModuleThreeActivity extends CaActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_three);
        setupScreen();
    }

    private void setupScreen() {
        setTitle(getResources().getString(R.string.nav_item_internal_training_module_3));
    }

}
