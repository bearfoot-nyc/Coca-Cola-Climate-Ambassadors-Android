package com.cocacola.climateambassador.modules;

import android.os.Bundle;
import android.view.Menu;

import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.ui.CaActivity;

public class BusinessCaseModule extends CaActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_case);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.business_case_module, menu);
        return true;
    }
    
}
