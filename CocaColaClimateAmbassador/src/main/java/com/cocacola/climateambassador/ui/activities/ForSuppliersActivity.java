package com.cocacola.climateambassador.ui.activities;

import android.os.Bundle;

import com.cocacola.climateambassador.R;

/**
 * Created by realandylawton on 8/31/13.
 */
public class ForSuppliersActivity extends ModuleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_suppliers);
        setupScreen();
    }

    private void setupScreen() {
        setTitle("For Suppliers");
    }

}
