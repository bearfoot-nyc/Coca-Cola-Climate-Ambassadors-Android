package com.cocacola.climateambassador.ui.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.models.Document;
import com.cocacola.climateambassador.util.JsonAssetsLoader;

/**
 * Created by realandylawton on 8/31/13.
 */
public class ModuleThreeActivity extends CaModuleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_three);
        setupScreen();
    }

    private void setupScreen() {
        setTitle(getResources().getString(R.string.nav_item_internal_training_module_3));
    }


    @Override
    public String getJsonAssetFilename() {
        // Relies on Fragment for parsing JSON
        return null;
    }

    @Override
    public void setupButtonAccordingToDocument(Document doc, View viewWithButton, LayoutInflater inflater) {
        // FIXME See comments in super class
    }

    @Override
    public String getFileType(String fileName) {
        // FIXME See comments in super class
        return "";
    }
}
