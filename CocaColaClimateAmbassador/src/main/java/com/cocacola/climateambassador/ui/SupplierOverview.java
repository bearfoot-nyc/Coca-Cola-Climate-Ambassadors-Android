package com.cocacola.climateambassador.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cocacola.climateambassador.R;

import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.Views;

public class SupplierOverview extends CaFragment {


    public static final String TAG = "SupplierOverview";

    @InjectView(R.id.introduction_button) Button introductionButton;
    @InjectView(R.id.video_button) Button videoButton;
    @InjectView(R.id.supplier_guide_button) Button supplierGuideButton;
    @InjectView(R.id.ingredient_button) Button ingredientButton;
    @InjectView(R.id.packaging_button) Button packagingButton;
    @InjectView(R.id.manufacturing_button) Button manufactureButton;
    @InjectView(R.id.distribution_button) Button distributionButton;
    @InjectView(R.id.refrigeration_button) Button refrigerationButton;

    public static SupplierOverview newInstance() {
        SupplierOverview frag = new SupplierOverview();
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.supplier_overview, container, false);
        Views.inject(this, view);

        introductionButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Play short intoduction
                Log.d(TAG, "Pressed intoduction Button");
            }
        });

        videoButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Play video
                Log.d(TAG, "Pressed video Button");
            }
        });

        supplierGuideButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Flip through supplier guide
                Log.d(TAG, "Pressed Supplier Guide Button");
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

    @OnClick(R.id.introduction_button)
    public void listenToIntoduction() {
        //TODO: Play short intoduction
        Log.d(TAG, "Pressed intoduction Button");
    }
}
