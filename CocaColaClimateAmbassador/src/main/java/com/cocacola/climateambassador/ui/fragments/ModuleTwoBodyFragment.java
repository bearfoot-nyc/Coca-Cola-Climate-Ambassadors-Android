package com.cocacola.climateambassador.ui.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.models.BulletPointFrame;
import com.cocacola.climateambassador.models.Module;
import com.cocacola.climateambassador.ui.activities.CaModuleBodyFragment;
import com.cocacola.climateambassador.ui.views.CourseMaterialsLayout;

/**
 * Created by Vinnie on 9/5/13.
 */
public class ModuleTwoBodyFragment extends CaModuleBodyFragment {

    @Override
    public String getJsonAssetFilename() {
        return "module_two.json";
    }

}
