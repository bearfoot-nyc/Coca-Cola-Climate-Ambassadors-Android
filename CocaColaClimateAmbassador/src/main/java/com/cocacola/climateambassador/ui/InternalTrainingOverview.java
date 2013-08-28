package com.cocacola.climateambassador.ui;

import android.os.Bundle;
import android.view.Menu;

import com.cocacola.climateambassador.R;

public class InternalTrainingOverview extends CaActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.internal_training_overview);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.internal_training_overview, menu);
        return true;
    }
    
}
