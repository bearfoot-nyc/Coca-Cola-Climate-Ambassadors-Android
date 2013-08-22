package com.cocacola.climateambassador.ui;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import com.cocacola.climateambassador.R;

import javax.inject.Inject;

import timber.log.Timber;

public class MainActivity extends CaActivity {

    @Inject Timber Log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Injection status: %s", "live");
    }
    
}
