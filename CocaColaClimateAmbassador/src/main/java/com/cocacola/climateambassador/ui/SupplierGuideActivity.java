package com.cocacola.climateambassador.ui;

import android.os.Bundle;
import android.view.Menu;

import com.cocacola.climateambassador.R;

public class SupplierGuideActivity extends CaActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.supplier_guide);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.supplier_guide, menu);
        return true;
    }
    
}
