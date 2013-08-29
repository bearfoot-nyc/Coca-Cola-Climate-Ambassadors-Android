package com.cocacola.climateambassador.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.cocacola.climateambassador.R;

public class InternalTrainingOverview extends CaFragment {


    public InternalTrainingOverview() {
        // STUB
    }

    public static InternalTrainingOverview newInstance() {
        InternalTrainingOverview fragment = new InternalTrainingOverview();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.internal_training_overview, container, false);



        return view;

    }
}
