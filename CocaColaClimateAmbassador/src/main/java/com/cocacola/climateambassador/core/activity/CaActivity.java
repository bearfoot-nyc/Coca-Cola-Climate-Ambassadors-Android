package com.cocacola.climateambassador.core.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import butterknife.Views;
import com.cocacola.climateambassador.core.CaApplication;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * Created by andrewlawton on 8/21/13.
 */
public class CaActivity extends FragmentActivity {

    @Inject
    Timber Log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CaApplication.getObjectGraph(getApplicationContext()).inject(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        Views.inject(this);
    }

}
