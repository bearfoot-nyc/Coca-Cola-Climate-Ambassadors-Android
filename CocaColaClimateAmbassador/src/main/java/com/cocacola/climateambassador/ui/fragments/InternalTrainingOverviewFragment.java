package com.cocacola.climateambassador.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cocacola.climateambassador.CaConstants;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.models.Document;
import com.cocacola.climateambassador.models.Module;
import com.cocacola.climateambassador.util.JsonAssetsLoader;

import java.io.IOException;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.Views;

/**
 * Created by realandylawton on 9/4/13.
 */
public class InternalTrainingOverviewFragment extends CaFragment {

    @InjectView(R.id.title) TextView mTitleView;
    @InjectView(R.id.bodyText) TextView mBodyTextView;
    @InjectView(R.id.textFramesLayout) LinearLayout mTextFramesLayout;
    @InjectView(R.id.bulletPointFramesLayout) LinearLayout mBulletPointsFramesLayout;

    @Inject
    JsonAssetsLoader mJsonAssetsLoader;

    protected Module mModule;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.frag_internal_training_overview, container, true);

        Views.inject(this, v);

        return v;
    }

    public Module getModule() {

        if(mModule == null) {
            try {
                mModule = mJsonAssetsLoader.parseModuleFromJsonFile("internal_training_overview.json");
            } catch (IOException e) {
                Toast.makeText(getActivity(), "Failed to Get Internal Training Overview", Toast.LENGTH_SHORT);
            }
        }

        return mModule;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Module module = getModule();

        mTitleView.setText(module.getTitle());
        mBodyTextView.setText(module.getBodyText());

    }

}
