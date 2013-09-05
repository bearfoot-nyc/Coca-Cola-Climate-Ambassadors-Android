package com.cocacola.climateambassador.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.ui.fragments.CaFragment;

import butterknife.InjectView;
import butterknife.Views;

/**
 * Created by Vinnie Vendemia on 8/28/13.
 */

public class ValueChainModule extends CaFragment {

    public static String TAG = "ValueChainModule";
    @InjectView(R.id.powerpoint_button) Button powerpointButton;
    @InjectView(R.id.movie_button) Button movieButton;
    @InjectView(R.id.doc_button) Button docButton;
    @InjectView(R.id.ingredient_button) Button ingredientButton;
    @InjectView(R.id.packaging_button) Button packagingButton;
    @InjectView(R.id.manufacturing_button) Button manufactureButton;
    @InjectView(R.id.distribution_button) Button distributionButton;
    @InjectView(R.id.refrigeration_button) Button refrigerationButton;


    public static ValueChainModule newInstance() {
        ValueChainModule frag = new ValueChainModule();
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.key_interventions, container, false);
        Views.inject(this, view);

        powerpointButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Open Powerpoint
                Log.d(TAG, "Clicked powerpoint Button");
            }
        });

        movieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Life of a Bottle Movie
                Log.d(TAG, "Clicked movie Button");
            }
        });

        docButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Open Notes
                Log.d(TAG, "Clicked Doc Button");
            }
        });

        ingredientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Go to Case Study
                Log.d(TAG, "Clicked Ingredient Button");
            }
        });

        packagingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Go to Case Study
                Log.d(TAG, "Clicked Packaging Button");
            }
        });

        manufactureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Go to Case Study
                Log.d(TAG, "Clicked Manufacturing Button");
            }
        });

        distributionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Go to Case Study
                Log.d(TAG, "Clicked Distribution Button");
            }
        });

        refrigerationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Go to Case Study
                Log.d(TAG, "Clicked Refrigeration Button");
            }
        });

        return view;
    }

}
