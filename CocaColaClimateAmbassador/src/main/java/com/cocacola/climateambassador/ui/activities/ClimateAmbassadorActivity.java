package com.cocacola.climateambassador.ui.activities;

import android.os.Bundle;

import com.cocacola.climateambassador.R;

/**
 * Created by realandylawton on 8/31/13.
 */
public class ClimateAmbassadorActivity extends CaActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_climate_ambassador);
        setupScreen();
    }

    private void setupScreen() {
        setTitle("Role of Climate Ambassador");
    }

}
