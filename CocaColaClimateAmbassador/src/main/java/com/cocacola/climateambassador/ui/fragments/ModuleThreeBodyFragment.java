package com.cocacola.climateambassador.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cocacola.climateambassador.CaConstants;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.models.Document;
import com.cocacola.climateambassador.ui.activities.CaActivity;
import com.cocacola.climateambassador.ui.activities.CaModuleBodyFragment;
import com.cocacola.climateambassador.ui.views.CourseMaterialsLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by realandylawton on 9/4/13.
 */
public class ModuleThreeBodyFragment extends CaModuleBodyFragment {

    @Override
    public String getJsonAssetFilename() {
        return "module_three.json";
    }

}
