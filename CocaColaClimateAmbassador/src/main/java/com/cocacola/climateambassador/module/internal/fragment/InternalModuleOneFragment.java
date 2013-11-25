package com.cocacola.climateambassador.module.internal.fragment;

import android.os.Bundle;
import com.cocacola.climateambassador.module.activity.AbsModuleActivity;
import com.cocacola.climateambassador.module.fragment.ModuleFragment;

/**
 * Created by Vinnie on 9/5/13.
 */
public class InternalModuleOneFragment extends ModuleFragment {

    public static InternalModuleOneFragment newInstance(Long moduleId) {

        InternalModuleOneFragment fragment = new InternalModuleOneFragment();

        Bundle bundle = new Bundle();
        bundle.putLong(AbsModuleActivity.MODULE_ID_BUNDLE_KEY, moduleId);
        fragment.setArguments(bundle);

        return fragment;

    }

    public String getJsonAssetFilename() {
        return "internal_module_one.json";
    }

}
