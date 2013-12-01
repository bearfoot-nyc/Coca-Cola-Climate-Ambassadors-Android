package com.cocacola.climateambassador.favorites.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.core.activity.CaDrawerSearchableActivity;

/**
 * Created by realandylawton on 12/1/13.
 */
public class FavoritesActivity extends CaDrawerSearchableActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites_activity);
    }

    @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
