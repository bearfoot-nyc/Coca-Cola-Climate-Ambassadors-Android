package com.cocacola.climateambassador.ui.activities;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.cocacola.climateambassador.models.Document;
import com.cocacola.climateambassador.models.Module;
import com.cocacola.climateambassador.util.JsonAssetsLoader;

import java.io.IOException;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by Vinnie on 9/5/13.
 */
public abstract class CaModuleActivity extends CaActivity {

    @Inject
    JsonAssetsLoader mJsonAssetsLoader;

    @Inject
    Timber Log;

    public abstract String getJsonAssetFilename();

    private Module mModule;

    public Module getModule() {

        // Lazily create Module object from JSON file
        if(mModule == null) {
            try {
                mModule = mJsonAssetsLoader.parseModuleFromJsonFile(getJsonAssetFilename());
            } catch (IOException e) {
                onAssetLoadError();
            }
        }

        return mModule;
    }

    private void onAssetLoadError() {
        Log.e("Failed loading %s", getJsonAssetFilename());
        Toast.makeText(this, "Failed To Load: " + getJsonAssetFilename(), Toast.LENGTH_SHORT);
    }

    // FIXME Move to utility class or create custom view
    public abstract void setupButtonAccordingToDocument(final Document doc, View viewWithButton, LayoutInflater inflater);

    // FIXME Should be moved to a utility class--DRY up code
    public abstract String getFileType(String fileName);

}
