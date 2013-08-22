package com.cocacola.climateambassador.ui;

import android.app.Activity;
import android.os.Bundle;

import com.cocacola.climateambassador.CaApplication;

/**
 * Created by andrewlawton on 8/21/13.
 */
public class CaActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CaApplication.getObjectGraph(getApplicationContext()).inject(this);
    }
}
