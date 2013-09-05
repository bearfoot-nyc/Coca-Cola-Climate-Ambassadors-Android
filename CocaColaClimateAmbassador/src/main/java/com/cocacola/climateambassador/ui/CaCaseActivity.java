package com.cocacola.climateambassador.ui;

import android.view.LayoutInflater;
import android.view.View;

import com.cocacola.climateambassador.models.Document;
import com.cocacola.climateambassador.util.JsonAssetsLoader;

/**
 * Created by Vinnie on 9/4/13.
 */
public abstract class CaCaseActivity extends CaActivity {

    public abstract void getCase();

    public abstract JsonAssetsLoader getAssetLoader();

    public abstract void setupButtonAccordingToDocument(final Document doc, View viewWithButton, LayoutInflater inflater);

    public abstract String getFileType(String fileName);
}
