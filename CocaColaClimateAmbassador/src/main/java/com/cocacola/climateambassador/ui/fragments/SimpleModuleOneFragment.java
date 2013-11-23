package com.cocacola.climateambassador.ui.fragments;

import android.os.Bundle;
import com.cocacola.climateambassador.ui.activities.ModuleActivity;

/**
 * Created by Vinnie on 9/5/13.
 */
public class SimpleModuleOneFragment extends SimpleModuleFragment {

    public static SimpleModuleOneFragment newInstance(Long moduleId) {

        SimpleModuleOneFragment fragment = new SimpleModuleOneFragment();

        Bundle bundle = new Bundle();
        bundle.putLong(ModuleActivity.MODULE_ID_BUNDLE_KEY, moduleId);
        fragment.setArguments(bundle);

        return fragment;

    }

    @Override
    public String getJsonAssetFilename() {
        return "module_one.json";
    }

}
