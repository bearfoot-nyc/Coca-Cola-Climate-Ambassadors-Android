package com.cocacola.climateambassador.ui;

import android.app.Activity;
import android.os.Bundle;

import com.cocacola.climateambassador.CaApplication;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by andrewlawton on 8/21/13.
 */
public class CaActivity extends Activity {

    @Inject
    Timber Log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CaApplication.getObjectGraph(getApplicationContext()).inject(this);
    }
}
