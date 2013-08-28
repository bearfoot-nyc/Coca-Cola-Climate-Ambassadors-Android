package com.cocacola.climateambassador.modules;

import android.os.Bundle;
import android.view.Menu;

import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.ui.CaActivity;

public class EngagingSuppliersModule extends CaActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.engaging_suppliers);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.engaging_suppliers_module, menu);
        return true;
    }
    
}
