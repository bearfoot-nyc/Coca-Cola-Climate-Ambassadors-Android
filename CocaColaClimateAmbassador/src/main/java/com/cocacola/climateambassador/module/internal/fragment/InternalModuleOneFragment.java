package com.cocacola.climateambassador.module.internal.fragment;

import android.os.Bundle;
import com.cocacola.climateambassador.module.activity.ModuleActivity;
import com.cocacola.climateambassador.module.fragment.SimpleModuleFragment;

/**
 * Created by Vinnie on 9/5/13.
 */
public class InternalModuleOneFragment extends SimpleModuleFragment {

    public static InternalModuleOneFragment newInstance(Long moduleId) {

        InternalModuleOneFragment fragment = new InternalModuleOneFragment();

        Bundle bundle = new Bundle();
        bundle.putLong(ModuleActivity.MODULE_ID_BUNDLE_KEY, moduleId);
        fragment.setArguments(bundle);

        return fragment;

    }

    public String getJsonAssetFilename() {
        return "module_one.json";
    }

}
