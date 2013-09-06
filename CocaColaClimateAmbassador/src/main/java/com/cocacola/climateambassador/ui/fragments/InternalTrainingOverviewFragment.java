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

import butterknife.InjectView;
import butterknife.Views;

/**
 * Created by realandylawton on 9/4/13.
 */
public class InternalTrainingOverviewFragment extends CaFragment {

    @InjectView(R.id.title) TextView mTitleView;
    @InjectView(R.id.body_text) TextView mBodyTextView;
    @InjectView(R.id.textFramesLayout) LinearLayout mTextFramesLayout;
    @InjectView(R.id.bulletPointFramesLayout) LinearLayout mBulletPointsFramesLayout;

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

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
