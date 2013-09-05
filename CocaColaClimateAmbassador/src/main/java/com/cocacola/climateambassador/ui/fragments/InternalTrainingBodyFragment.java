package com.cocacola.climateambassador.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cocacola.climateambassador.R;

import butterknife.InjectView;

/**
 * Created by realandylawton on 9/4/13.
 */
public class InternalTrainingBodyFragment extends CaFragment {

    @InjectView(R.id.powerpoint_button) Button powerpointButton;
    @InjectView(R.id.movie_button) Button movieButton;
    @InjectView(R.id.doc_button) Button docButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.frag_internal_training, container, true);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
