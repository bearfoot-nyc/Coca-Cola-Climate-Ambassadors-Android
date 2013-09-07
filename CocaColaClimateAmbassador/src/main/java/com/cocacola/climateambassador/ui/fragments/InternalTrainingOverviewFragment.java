package com.cocacola.climateambassador.ui.fragments;

import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cocacola.climateambassador.CaConstants;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.models.BulletPointFrame;
import com.cocacola.climateambassador.models.Document;
import com.cocacola.climateambassador.models.Module;
import com.cocacola.climateambassador.ui.activities.CaModuleBodyFragment;
import com.cocacola.climateambassador.ui.views.CourseMaterialsLayout;
import com.cocacola.climateambassador.util.JsonAssetsLoader;

import java.io.IOException;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.Views;

/**
 * Created by realandylawton on 9/4/13.
 */
public class InternalTrainingOverviewFragment extends CaModuleBodyFragment {

    @Override
    public String getJsonAssetFilename() {
        return "internal_training_overview.json";
    }

}
