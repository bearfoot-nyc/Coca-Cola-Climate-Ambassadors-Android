package com.cocacola.climateambassador.ui.activities;

import android.os.Bundle;

import com.cocacola.climateambassador.R;

/**
 * Created by realandylawton on 8/31/13.
 */
public class InternalTrainingActivity extends ModuleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_training);
        setupScreen();
    }

    private void setupScreen() {
        setTitle("Internal Training Materials");
    }

}
