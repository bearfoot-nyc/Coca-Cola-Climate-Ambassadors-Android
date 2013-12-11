package com.cocacola.climateambassador.core.controller;

import android.app.Activity;
import com.cocacola.climateambassador.core.CaApplication;

/**
 * Created by realandylawton on 12/10/13.
 */
public class CaController {

    protected Activity mActivity;

    public CaController(Activity activity) {
        mActivity = activity;
        CaApplication.getObjectGraph(mActivity).inject(this);
    }
}
