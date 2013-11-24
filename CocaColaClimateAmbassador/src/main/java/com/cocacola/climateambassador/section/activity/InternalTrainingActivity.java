package com.cocacola.climateambassador.section.activity;

import android.os.Bundle;

import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.module.activity.ModuleActivity;

/**
 * Created by realandylawton on 8/31/13.
 */
public class InternalTrainingActivity extends ModuleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.section_activity_internal_training);
        setupScreen();
    }

    private void setupScreen() {
        setTitle("Internal Training Materials");
    }

}
