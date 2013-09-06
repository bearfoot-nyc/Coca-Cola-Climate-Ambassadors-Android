package com.cocacola.climateambassador.ui.activities;

import android.view.LayoutInflater;
import android.view.View;

import com.cocacola.climateambassador.models.Document;
import com.cocacola.climateambassador.util.JsonAssetsLoader;

/**
 * Created by Vinnie on 9/5/13.
 */
public abstract class CaModuleActivity extends CaActivity {


    public abstract JsonAssetsLoader getAssetLoader();
    public abstract void getModule();
    public abstract void setupButtonAccordingToDocument(final Document doc, View viewWithButton, LayoutInflater inflater);
    public abstract String getFileType(String fileName);
}
