package com.cocacola.climateambassador.ui;

import android.os.Bundle;

import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.adapters.MenuListAdapter;
import com.cocacola.climateambassador.models.NavigationDrawerItem;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by realandylawton on 8/31/13.
 */
public class InternalTrainingActivity extends SectionActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_training);
        setupNavigationDrawer();
        setupScreen();
    }

    private void setupScreen() {
        setTitle("Internal Training Materialsl");
    }

}
