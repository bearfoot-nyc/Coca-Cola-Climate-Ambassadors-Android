package com.cocacola.climateambassador.module.suppliers.fragment;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Views;
import com.cocacola.climateambassador.core.util.HasJsonModel;
import com.cocacola.climateambassador.data.Module;
import com.cocacola.climateambassador.module.activity.ModuleActivity;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.data.json.CaseJson;
import com.cocacola.climateambassador.core.views.BulletPointLayout;
import com.cocacola.climateambassador.core.util.JsonAssetsLoader;
import com.cocacola.climateambassador.core.util.Toaster;
import com.cocacola.climateambassador.module.fragment.ModuleFragment;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;

import javax.inject.Inject;

import butterknife.InjectView;

/**
 * Created by realandylawton on 9/7/13.
 */
public class ForSuppliersVisionFragment extends ModuleFragment {

    public static ForSuppliersVisionFragment newInstance(Long moduleId) {

        ForSuppliersVisionFragment fragment = new ForSuppliersVisionFragment();
        fragment.setArguments(ModuleFragment.createBundleWithModuleId(moduleId));

        return fragment;

    }

    @InjectView(R.id.title) protected TextView mTitleView;
    @InjectView(R.id.body_text) protected TextView mBodyTextView;
    @InjectView(R.id.bullet_points) protected BulletPointLayout mBulletPointLayout;
    @InjectView(R.id.vision_callout) protected TextView mCalloutView;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.suppliers_frag_2020_vision, null);

        Views.inject(this, v);
        return v;

    }

    @Override public void onViewCreated(View v, Bundle savedInstanceState) {

        Module module = getModule();

        mTitleView.setText(module.getTitle());
        mBodyTextView.setText(Html.fromHtml(module.getBodyText()));

        mBulletPointLayout.setBulletPoints(module.getBulletPointFrame().getBulletPoints());

        // FIXME
        //mCalloutView.setText(module.getTextFrames().get(0).getBodyText());

    }

}
