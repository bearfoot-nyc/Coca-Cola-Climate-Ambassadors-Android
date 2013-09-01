package com.cocacola.climateambassador.ui;

import android.os.Bundle;

import com.cocacola.climateambassador.R;

/**
 * Created by realandylawton on 8/31/13.
 */
public class ForSuppliersActivity extends CaDrawerActivity{

    private static final int DRAWER_POSITION_MODULE_1 = 0;
    private static final int DRAWER_POSITION_MODULE_2 = 1;
    private static final int DRAWER_POSITION_MODULE_3 = 2;
    private static final int DRAWER_POSITION_MODULE_4 = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_training);
        setupNavigationDrawer();
    }

    @Override
    int getNavigationTitleArrayId() {
        return R.array.nav_internal_titles;
    }

    @Override
    int getNavigationIconArrayId() {
        return R.array.nav_internal_icons;
    }

    @Override
    void onDrawerItemClick(int position) {
        switch (position) {
            case DRAWER_POSITION_MODULE_1:
                break;
            case DRAWER_POSITION_MODULE_2:
                break;
            case DRAWER_POSITION_MODULE_3:
                break;
            case DRAWER_POSITION_MODULE_4:
                break;
        }
    }

}
